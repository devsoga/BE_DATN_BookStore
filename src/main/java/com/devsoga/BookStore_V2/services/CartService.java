package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.CartRequest;
import com.devsoga.BookStore_V2.dtos.responses.CartRespone;
import com.devsoga.BookStore_V2.enties.CartEntity;
import com.devsoga.BookStore_V2.enties.CustomerEntity;
import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.CartRepository;
import com.devsoga.BookStore_V2.repositories.CustomerRepository;
import com.devsoga.BookStore_V2.repositories.ProductRepository;
import com.devsoga.BookStore_V2.repositories.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;
    
    @Autowired
    private com.devsoga.BookStore_V2.repositories.PromotionRepository promotionRepository;

    public BaseRespone createCart(CartRequest request) {
        BaseRespone resp = new BaseRespone();
        try {
            if (request.getCustomerCode() == null || request.getProductCode() == null || request.getQuantity() == null) {
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

            BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(product.getProductCode()).orElse(BigDecimal.ZERO);
            // resolve promotion (if any) to compute discount and discounted unit price
            java.math.BigDecimal discountValue = null;
            java.math.BigDecimal discountedUnitPrice = unitPrice;
            if (product.getPromotionCode() != null && !product.getPromotionCode().isBlank()) {
                com.devsoga.BookStore_V2.enties.PromotionEntity promo = promotionRepository.findByPromotionCode(product.getPromotionCode()).orElse(null);
                if (promo != null && isPromotionValid(promo)) {
                    discountValue = promo.getValue();
                    if (promo.getPromotionTypeEntity() != null && "PT_PERCENT".equalsIgnoreCase(promo.getPromotionTypeEntity().getPromotionTypeCode())) {
                        java.math.BigDecimal discountAmount = unitPrice.multiply(promo.getValue());
                        discountedUnitPrice = unitPrice.subtract(discountAmount);
                    } else if (promo.getPromotionTypeEntity() != null && "PT_FIXED".equalsIgnoreCase(promo.getPromotionTypeEntity().getPromotionTypeCode())) {
                        discountedUnitPrice = unitPrice.subtract(promo.getValue());
                        if (discountedUnitPrice.compareTo(java.math.BigDecimal.ZERO) < 0) discountedUnitPrice = java.math.BigDecimal.ZERO;
                    }
                }
            }
            int qty = Math.max(1, request.getQuantity());

            // check existing cart item
            CartEntity existing = cartRepository
                    .findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(customer.getCustomerCode(), product.getProductCode())
                    .orElse(null);

            CartEntity saved;
            boolean isNew = false;
            if (existing != null) {
                int newQty = (existing.getQuantity() != null ? existing.getQuantity() : 0) + qty;
                existing.setQuantity(newQty);
                existing.setPromotionCode(product.getPromotionCode());
                existing.setDiscountValue(discountValue);
                existing.setTotalAmount(discountedUnitPrice.multiply(BigDecimal.valueOf(newQty)));
                saved = cartRepository.save(existing);
            } else {
                CartEntity c = new CartEntity();
                c.setProductEntity(product);
                c.setCustomerEntity(customer);
                c.setPromotionCode(product.getPromotionCode());
                c.setQuantity(qty);
                c.setDiscountValue(discountValue);
                c.setTotalAmount(discountedUnitPrice.multiply(BigDecimal.valueOf(qty)));
                saved = cartRepository.save(c);
                isNew = true;
            }

            // return the created/updated cart item in response data
            resp.setStatusCode(isNew ? HttpStatus.CREATED.value() : HttpStatus.OK.value());
            resp.setMessage(isNew ? "Cart created" : "Cart updated");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create cart: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getCartByCustomerCode(String customerCode) {
        BaseRespone resp = new BaseRespone();
        try {
            List<CartEntity> list = cartRepository.findByCustomerEntity_CustomerCode(customerCode);
            List<CartRespone> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch cart: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteCartItem(String customerCode, String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            CartEntity existing = cartRepository
                    .findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(customerCode, productCode)
                    .orElse(null);

            if (existing == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Cart item not found");
                resp.setData(null);
                return resp;
            }

            CartRespone deleted = toResp(existing);
            cartRepository.delete(existing);

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Cart item deleted");
            resp.setData(deleted);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete cart item: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone updateCartQuantity(CartRequest request) {
        BaseRespone resp = new BaseRespone();
        try {
            if (request.getCustomerCode() == null || request.getProductCode() == null || request.getQuantity() == null) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Missing required fields");
                resp.setData(null);
                return resp;
            }

            CartEntity existing = cartRepository
                    .findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(request.getCustomerCode(), request.getProductCode())
                    .orElse(null);

            if (existing == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Cart item not found");
                resp.setData(null);
                return resp;
            }

            int qty = request.getQuantity();
            if (qty <= 0) {
                CartRespone deleted = toResp(existing);
                cartRepository.delete(existing);
                resp.setStatusCode(HttpStatus.OK.value());
                resp.setMessage("Cart item deleted because quantity <= 0");
                resp.setData(deleted);
                return resp;
            }

            BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(existing.getProductEntity().getProductCode()).orElse(BigDecimal.ZERO);
            existing.setQuantity(qty);
            existing.setTotalAmount(unitPrice.multiply(BigDecimal.valueOf(qty)));
            CartEntity saved = cartRepository.save(existing);

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Cart quantity updated");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update cart quantity: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteAllByCustomerCode(String customerCode) {
        BaseRespone resp = new BaseRespone();
        try {
            java.util.List<CartEntity> list = cartRepository.findByCustomerEntity_CustomerCode(customerCode);
            if (list == null || list.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("No cart items found for customer: " + customerCode);
                resp.setData(null);
                return resp;
            }

            java.util.List<CartRespone> deleted = list.stream().map(this::toResp).collect(java.util.stream.Collectors.toList());
            cartRepository.deleteAll(list);

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted " + deleted.size() + " cart items");
            resp.setData(deleted);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete cart items: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private CartRespone toResp(CartEntity c) {
        if (c == null) return null;
        CartRespone r = new CartRespone();
        r.setId(c.getId());
        if (c.getProductEntity() != null) {
            r.setProductCode(c.getProductEntity().getProductCode());
            r.setProductName(c.getProductEntity().getProductName());
            r.setImage(c.getProductEntity().getImage());
            BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(c.getProductEntity().getProductCode()).orElse(BigDecimal.ZERO);
            r.setUnitPrice(unitPrice);
        }
        r.setPromotionCode(c.getPromotionCode());
        r.setQuantity(c.getQuantity());
        r.setTotalAmount(c.getTotalAmount());
        r.setDiscountValue(c.getDiscountValue());
        return r;
    }

    private boolean isPromotionValid(com.devsoga.BookStore_V2.enties.PromotionEntity promotion) {
        if (promotion == null || !Boolean.TRUE.equals(promotion.getStatus())) return false;
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        if (promotion.getStartDate() != null) {
            java.time.LocalDateTime s = promotion.getStartDate().toLocalDateTime();
            if (now.isBefore(s)) return false;
        }
        if (promotion.getEndDate() != null) {
            java.time.LocalDateTime e = promotion.getEndDate().toLocalDateTime();
            if (now.isAfter(e)) return false;
        }
        return true;
    }
}
