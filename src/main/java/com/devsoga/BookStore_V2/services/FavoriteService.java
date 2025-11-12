package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.FavoriteRequest;
import com.devsoga.BookStore_V2.dtos.responses.FavoriteRespone;
import com.devsoga.BookStore_V2.enties.FavoriteEntity;
import com.devsoga.BookStore_V2.enties.CustomerEntity;
import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.CustomerRepository;
import com.devsoga.BookStore_V2.repositories.FavoriteRepository;
import com.devsoga.BookStore_V2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private com.devsoga.BookStore_V2.repositories.PriceHistoryRepository priceHistoryRepository;

    public BaseRespone createFavorite(FavoriteRequest request) {
        BaseRespone resp = new BaseRespone();
        try {
            if (request.getCustomerCode() == null || request.getProductCode() == null) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Missing required fields");
                resp.setData(null);
                return resp;
            }

            ProductEntity product = productRepository.findByProductCode(request.getProductCode()).orElse(null);
            if (product == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Product not found");
                resp.setData(null);
                return resp;
            }

            CustomerEntity customer = customerRepository.findByCustomerCode(request.getCustomerCode()).orElse(null);
            if (customer == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Customer not found");
                resp.setData(null);
                return resp;
            }

            FavoriteEntity existing = favoriteRepository
                    .findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(customer.getCustomerCode(), product.getProductCode())
                    .orElse(null);

            FavoriteEntity saved;
            boolean isNew = false;
            if (existing != null) {
                saved = existing;
            } else {
                FavoriteEntity f = new FavoriteEntity();
                f.setCustomerEntity(customer);
                f.setProductEntity(product);
                saved = favoriteRepository.save(f);
                isNew = true;
            }

            resp.setStatusCode(isNew ? HttpStatus.CREATED.value() : HttpStatus.OK.value());
            resp.setMessage(isNew ? "Favorite created" : "Favorite exists");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create favorite: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getFavoritesByCustomerCode(String customerCode) {
        BaseRespone resp = new BaseRespone();
        try {
            List<FavoriteEntity> list = favoriteRepository.findByCustomerEntity_CustomerCodeOrderByCreatedDateDesc(customerCode);
            List<FavoriteRespone> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch favorites: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteFavorite(String customerCode, String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            FavoriteEntity existing = favoriteRepository
                    .findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(customerCode, productCode)
                    .orElse(null);

            if (existing == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Favorite not found");
                resp.setData(null);
                return resp;
            }

            FavoriteRespone deleted = toResp(existing);
            favoriteRepository.delete(existing);

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Favorite deleted");
            resp.setData(deleted);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete favorite: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getFavorite(String customerCode, String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            FavoriteEntity existing = favoriteRepository
                    .findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(customerCode, productCode)
                    .orElse(null);

            if (existing == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Favorite not found");
                resp.setData(null);
                return resp;
            }

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(existing));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch favorite: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private FavoriteRespone toResp(FavoriteEntity f) {
        if (f == null) return null;
        FavoriteRespone r = new FavoriteRespone();
        r.setId(f.getId());
        if (f.getProductEntity() != null) {
            r.setProductCode(f.getProductEntity().getProductCode());
            r.setProductName(f.getProductEntity().getProductName());
            r.setImage(f.getProductEntity().getImage());
            java.math.BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(f.getProductEntity().getProductCode()).orElse(java.math.BigDecimal.ZERO);
            r.setUnitPrice(unitPrice);
        }
        // map created date if available
        try {
            r.setCreatedDate(f.getCreatedDate());
        } catch (Exception ignore) {
            // if entity not auditable or createdDate missing, ignore
        }
        return r;
    }
}
