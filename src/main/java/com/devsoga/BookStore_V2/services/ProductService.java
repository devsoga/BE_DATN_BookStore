package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.enties.PromotionEntity;
import com.devsoga.BookStore_V2.dtos.responses.ProductRespone;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.ProductRepository;
import com.devsoga.BookStore_V2.repositories.PriceHistoryRepository;
import com.devsoga.BookStore_V2.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @org.springframework.beans.factory.annotation.Autowired
    private com.devsoga.BookStore_V2.repositories.ProductCategoryRepository productCategoryRepository;

    @org.springframework.beans.factory.annotation.Autowired
    private com.devsoga.BookStore_V2.repositories.InventoryRepository inventoryRepository;

    @Value("${app.base-url:}")
    private String appBaseUrl; // optional, e.g. http://localhost:8080

    public BaseRespone getAllProducts() {
        BaseRespone resp = new BaseRespone();
        try {
        // only return products with status = true
        List<ProductEntity> list = productRepository.findAll().stream()
            .filter(p -> Boolean.TRUE.equals(p.getStatus()))
            .collect(Collectors.toList());
        List<ProductRespone> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch products: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getProductByProductCode(String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            ProductEntity product = productRepository.findByProductCode(productCode).orElse(null);
            if (product == null || !Boolean.TRUE.equals(product.getStatus())) {
                // not found or not active
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Product not found");
                resp.setData(null);
            } else {
                ProductRespone dto = toResp(product);
                resp.setStatusCode(HttpStatus.OK.value());
                resp.setMessage("Success");
                resp.setData(dto);
            }
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch product: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone searchProducts(String q) {
        BaseRespone resp = new BaseRespone();
        try {
            java.util.List<ProductEntity> list;
            if (q == null || q.isBlank()) {
                list = productRepository.findAll();
            } else {
                String keyword = q.trim();
                list = productRepository.findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrProductCodeContainingIgnoreCaseOrderByCreatedDateDesc(
                        keyword, keyword, keyword
                );
            }
            java.util.List<ProductRespone> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to search products: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getProductsByCategoryCode(String categoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            if (categoryCode == null || categoryCode.isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("categoryCode is required");
                resp.setData(null);
                return resp;
            }
            java.util.List<ProductEntity> list = productRepository.findByProductCategoryEntity_CategoryCode(categoryCode);
            java.util.List<ProductRespone> data = list.stream()
                    .filter(p -> Boolean.TRUE.equals(p.getStatus()))
                    .map(this::toResp)
                    .collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch products by category: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getProductsByCategoryType(String categoryType) {
        BaseRespone resp = new BaseRespone();
        try {
            if (categoryType == null || categoryType.isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("categoryType is required");
                resp.setData(null);
                return resp;
            }
            java.util.List<ProductEntity> list = productRepository.findByProductCategoryEntity_CategoryType(categoryType);
            java.util.List<ProductRespone> data = list.stream()
                    .filter(p -> Boolean.TRUE.equals(p.getStatus()))
                    .map(this::toResp)
                    .collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch products by category type: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    // --- Create product
    public BaseRespone createProduct(com.devsoga.BookStore_V2.dtos.requests.ProductCreateRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getProductCode() == null || req.getProductCode().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("productCode is required");
                resp.setData(null);
                return resp;
            }

            if (productRepository.findByProductCode(req.getProductCode()).isPresent()) {
                resp.setStatusCode(HttpStatus.CONFLICT.value());
                resp.setMessage("Product already exists");
                resp.setData(null);
                return resp;
            }

            ProductEntity entity = new ProductEntity();
            entity.setProductCode(req.getProductCode());
            entity.setProductName(req.getProductName());
            entity.setDescription(req.getDescription());
            entity.setImage(req.getImage());
            // default status to true when not provided
            if (req.getStatus() == null) {
                entity.setStatus(Boolean.TRUE);
            } else {
                entity.setStatus(req.getStatus());
            }
            entity.setPromotionCode(req.getPromotionCode());
            entity.setAuthor(req.getAuthor());
            entity.setPublisher(req.getPublisher());

            if (req.getCategoryCode() != null && !req.getCategoryCode().isBlank()) {
                var cat = productCategoryRepository.findByCategoryCode(req.getCategoryCode()).orElse(null);
                entity.setProductCategoryEntity(cat);
            }

            ProductEntity saved = productRepository.save(entity);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create product: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    // --- Update product
    public BaseRespone updateProduct(String productCode, com.devsoga.BookStore_V2.dtos.requests.ProductRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            ProductEntity exist = productRepository.findByProductCode(productCode).orElse(null);
            if (exist == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Product not found");
                resp.setData(null);
                return resp;
            }

            if (req.getProductName() != null) exist.setProductName(req.getProductName());
            if (req.getDescription() != null) exist.setDescription(req.getDescription());
            if (req.getImage() != null) exist.setImage(req.getImage());
            if (req.getAuthor() != null) exist.setAuthor(req.getAuthor());
            if (req.getPublisher() != null) exist.setPublisher(req.getPublisher());
            if (req.getStatus() != null) exist.setStatus(req.getStatus());
            if (req.getPromotionCode() != null) exist.setPromotionCode(req.getPromotionCode());

            if (req.getCategoryCode() != null) {
                var cat = productCategoryRepository.findByCategoryCode(req.getCategoryCode()).orElse(null);
                exist.setProductCategoryEntity(cat);
            }

            ProductEntity saved = productRepository.save(exist);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update product: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    // --- Delete product
    public BaseRespone deleteProduct(String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            ProductEntity exist = productRepository.findByProductCode(productCode).orElse(null);
            if (exist == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Product not found");
                resp.setData(null);
                return resp;
            }
            // soft-delete: set status = false instead of removing from DB
            exist.setStatus(Boolean.FALSE);
            productRepository.save(exist);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted (soft)");
            resp.setData(null);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete product: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public ProductRespone toResp(ProductEntity p) {
        ProductRespone r = new ProductRespone();
        if (p == null) return null;
        r.setProductCode(p.getProductCode());
        r.setProductName(p.getProductName());
        r.setDescription(p.getDescription());
        // normalize image path: ensure it starts with '/' and optionally prefix base URL
        String img = p.getImage();
        if (img != null) {
            img = img.replace('\\', '/'); // normalize backslashes if any
            if (!img.startsWith("/")) img = "/" + img;
            if (appBaseUrl != null && !appBaseUrl.isBlank()) {
                // avoid double slashes when prefixing
                String base = appBaseUrl.endsWith("/") ? appBaseUrl.substring(0, appBaseUrl.length()-1) : appBaseUrl;
                img = base + img;
            }
        }
        r.setImage(img);
        r.setAuthor(p.getAuthor());
    r.setPublisher(p.getPublisher());
        
        // Fetch latest active price from price_history
        BigDecimal price = priceHistoryRepository.findLatestActivePriceByProductCode(p.getProductCode())
                .orElse(BigDecimal.ZERO);
        r.setPrice(price);
        
        // Fetch latest import price from import_invoice_detail
        BigDecimal importPrice = purchaseOrderDetailRepository.findLatestImportPriceByProductCode(p.getProductCode())
                .orElse(BigDecimal.ZERO);
        r.setImportPrice(importPrice);
        
        // Calculate discounted price if product has valid promotion
        BigDecimal discountedPrice = price;
        PromotionEntity promotion = null;
        
        if (p.getPromotionCode() != null && !p.getPromotionCode().isBlank()) {
            promotion = promotionRepository.findByPromotionCode(p.getPromotionCode()).orElse(null);
            if (promotion != null && isPromotionValid(promotion)) {
                r.setPromotionCode(promotion.getPromotionCode());
                r.setPromotionName(promotion.getPromotionName());
                r.setDiscountValue(promotion.getValue());
                
                // Calculate discounted price based on promotion type
                if (promotion.getPromotionTypeEntity() != null) {
                    String promotionType = promotion.getPromotionTypeEntity().getPromotionTypeCode();
                    if ("PT_PERCENT".equalsIgnoreCase(promotionType)) {
                        // Percentage discount
                        BigDecimal discountAmount = price.multiply(promotion.getValue());
                        discountedPrice = price.subtract(discountAmount);
                    } else if ("PT_FIXED".equalsIgnoreCase(promotionType)) {
                        // Fixed amount discount
                        discountedPrice = price.subtract(promotion.getValue());
                        if (discountedPrice.compareTo(BigDecimal.ZERO) < 0) {
                            discountedPrice = BigDecimal.ZERO;
                        }
                    }
                }
            }
        }
        
        try {
            if (p.getProductCategoryEntity() != null) {
                r.setCategoryCode(p.getProductCategoryEntity().getCategoryCode());
                r.setCategoryName(p.getProductCategoryEntity().getCategoryName());
            }
        } catch (Exception ignored) {
            // in case of lazy loading issues, ignore and leave categoryCode null
        }
        // fetch total active inventory quantity for this product
        try {
            Integer total = inventoryRepository.findTotalQuantityByProductCode(p.getProductCode());
            r.setStockQuantity(total == null ? 0 : total);
        } catch (Exception ignored) {
            r.setStockQuantity(0);
        }
        return r;
    }
    
    private boolean isPromotionValid(PromotionEntity promotion) {
        if (promotion == null || !Boolean.TRUE.equals(promotion.getStatus())) {
            return false;
        }
        
        LocalDateTime now = LocalDateTime.now();
        
        // Check start date
        if (promotion.getStartDate() != null) {
            LocalDateTime startDate = promotion.getStartDate().toLocalDateTime();
            if (now.isBefore(startDate)) {
                return false;
            }
        }
        
        // Check end date
        if (promotion.getEndDate() != null) {
            LocalDateTime endDate = promotion.getEndDate().toLocalDateTime();
            if (now.isAfter(endDate)) {
                return false;
            }
        }
        
        return true;
    }
}
