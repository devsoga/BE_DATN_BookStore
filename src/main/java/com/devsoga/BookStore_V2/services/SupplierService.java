package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.SupplierRequest;
import com.devsoga.BookStore_V2.dtos.responses.SupplierResponse;
import com.devsoga.BookStore_V2.enties.SupplierEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private com.devsoga.BookStore_V2.repositories.ProductRepository productRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.SupplierProductRepository supplierProductRepository;

    @Autowired
    private com.devsoga.BookStore_V2.services.ProductService productService;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
        // return all suppliers including those with status = false
        List<SupplierEntity> list = supplierRepository.findAll();
        List<SupplierResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getById(String supplierCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = supplierRepository.findBySupplierCode(supplierCode);
            if (opt.isEmpty() || !Boolean.TRUE.equals(opt.get().getStatus())) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Supplier not found or inactive");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone create(SupplierRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getSupplierCode() == null || req.getSupplierCode().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("supplierCode is required");
                resp.setData(null);
                return resp;
            }
            if (supplierRepository.findBySupplierCode(req.getSupplierCode()).isPresent()) {
                resp.setStatusCode(HttpStatus.CONFLICT.value());
                resp.setMessage("Supplier already exists");
                resp.setData(null);
                return resp;
            }
            SupplierEntity e = new SupplierEntity();
            e.setSupplierCode(req.getSupplierCode());
            e.setSupplierName(req.getSupplierName());
            e.setAddress(req.getAddress());
            e.setDescription(req.getDescription());
            e.setPhoneNumber(req.getPhoneNumber());
            e.setEmail(req.getEmail());
            e.setStatus(req.getStatus() == null ? Boolean.TRUE : req.getStatus());
            SupplierEntity saved = supplierRepository.save(e);
            // handle productProvide if provided (create supplier_product rows)
            try {
                if (req.getProductProvide() != null) {
                    for (com.devsoga.BookStore_V2.dtos.requests.SupplierProductRequest spReq : req.getProductProvide()) {
                                if (spReq == null) continue;
                                // Try to find existing mapping by supplierProductCode first (if provided)
                                com.devsoga.BookStore_V2.enties.SupplierProductEntity ep = null;
                                if (spReq.getSupplierProductCode() != null && !spReq.getSupplierProductCode().isBlank()) {
                                    var byCode = supplierProductRepository.findBySupplierProductCode(spReq.getSupplierProductCode());
                                    if (byCode.isPresent()) ep = byCode.get();
                                }
                                // If not found by supplierProductCode, try supplierCode+productCode
                                if (ep == null && spReq.getProductCode() != null) {
                                    var byPair = supplierProductRepository.findBySupplierCodeAndProductCode(saved.getSupplierCode(), spReq.getProductCode());
                                    if (byPair.isPresent()) ep = byPair.get();
                                }

                                if (ep != null) {
                                    if (spReq.getImportPrice() != null) ep.setImportPrice(spReq.getImportPrice());
                                    if (spReq.getStatus() != null) ep.setStatus(spReq.getStatus());
                                    if (spReq.getSupplierProductCode() != null && !spReq.getSupplierProductCode().isBlank()) ep.setSupplierProductCode(spReq.getSupplierProductCode());
                                    if (spReq.getProductCode() != null && !spReq.getProductCode().isBlank()) ep.setProductCode(spReq.getProductCode());
                                    supplierProductRepository.save(ep);
                                } else {
                                    // create new mapping if we have a productCode
                                    if (spReq.getProductCode() == null) continue; // nothing to create or update
                                    com.devsoga.BookStore_V2.enties.SupplierProductEntity spe = new com.devsoga.BookStore_V2.enties.SupplierProductEntity();
                                    String code = spReq.getSupplierProductCode();
                                    if (code == null || code.isBlank()) code = saved.getSupplierCode() + "_" + spReq.getProductCode();
                                    spe.setSupplierProductCode(code);
                                    spe.setSupplierCode(saved.getSupplierCode());
                                    spe.setProductCode(spReq.getProductCode());
                                    spe.setImportPrice(spReq.getImportPrice());
                                    spe.setStatus(spReq.getStatus() == null ? Boolean.TRUE : spReq.getStatus());
                                    supplierProductRepository.save(spe);
                                }
                    }
                }
            } catch (Exception ignore) {
                // don't fail supplier creation if supplier_product processing fails
            }
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String supplierCode, SupplierRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = supplierRepository.findBySupplierCode(supplierCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Supplier not found");
                resp.setData(null);
                return resp;
            }
            SupplierEntity e = opt.get();
            if (req.getSupplierCode() != null && !req.getSupplierCode().isBlank()) e.setSupplierCode(req.getSupplierCode());
            if (req.getSupplierName() != null) e.setSupplierName(req.getSupplierName());
            if (req.getAddress() != null) e.setAddress(req.getAddress());
            if (req.getDescription() != null) e.setDescription(req.getDescription());
            if (req.getPhoneNumber() != null) e.setPhoneNumber(req.getPhoneNumber());
            if (req.getEmail() != null) e.setEmail(req.getEmail());
            if (req.getStatus() != null) e.setStatus(req.getStatus());
            SupplierEntity saved = supplierRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            // handle productProvide upserts
            try {
                if (req.getProductProvide() != null) {
                    for (com.devsoga.BookStore_V2.dtos.requests.SupplierProductRequest spReq : req.getProductProvide()) {
                        if (spReq == null) continue;
                        // try find by supplierProductCode first
                        com.devsoga.BookStore_V2.enties.SupplierProductEntity ep = null;
                        if (spReq.getSupplierProductCode() != null && !spReq.getSupplierProductCode().isBlank()) {
                            var byCode = supplierProductRepository.findBySupplierProductCode(spReq.getSupplierProductCode());
                            if (byCode.isPresent()) ep = byCode.get();
                        }
                        if (ep == null && spReq.getProductCode() != null) {
                            var byPair = supplierProductRepository.findBySupplierCodeAndProductCode(saved.getSupplierCode(), spReq.getProductCode());
                            if (byPair.isPresent()) ep = byPair.get();
                        }
                        if (ep != null) {
                            if (spReq.getImportPrice() != null) ep.setImportPrice(spReq.getImportPrice());
                            if (spReq.getStatus() != null) ep.setStatus(spReq.getStatus());
                            if (spReq.getSupplierProductCode() != null && !spReq.getSupplierProductCode().isBlank()) ep.setSupplierProductCode(spReq.getSupplierProductCode());
                            if (spReq.getProductCode() != null && !spReq.getProductCode().isBlank()) ep.setProductCode(spReq.getProductCode());
                            supplierProductRepository.save(ep);
                        } else {
                            // create only if productCode provided
                            if (spReq.getProductCode() == null) continue;
                            com.devsoga.BookStore_V2.enties.SupplierProductEntity spe = new com.devsoga.BookStore_V2.enties.SupplierProductEntity();
                            String code = spReq.getSupplierProductCode();
                            if (code == null || code.isBlank()) code = saved.getSupplierCode() + "_" + spReq.getProductCode();
                            spe.setSupplierProductCode(code);
                            spe.setSupplierCode(saved.getSupplierCode());
                            spe.setProductCode(spReq.getProductCode());
                            spe.setImportPrice(spReq.getImportPrice());
                            spe.setStatus(spReq.getStatus() == null ? Boolean.TRUE : spReq.getStatus());
                            supplierProductRepository.save(spe);
                        }
                    }
                }
            } catch (Exception ignore) {
                // don't fail supplier update if supplier_product processing fails
            }
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String supplierCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = supplierRepository.findBySupplierCode(supplierCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Supplier not found");
                resp.setData(null);
                return resp;
            }
            SupplierEntity e = opt.get();
            // soft delete
            e.setStatus(Boolean.FALSE);
            supplierRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted (soft)");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getProductsBySupplier(String supplierCode) {
        BaseRespone resp = new BaseRespone();
        try {
            if (supplierCode == null || supplierCode.isBlank()) {
                resp.setStatusCode(org.springframework.http.HttpStatus.BAD_REQUEST.value());
                resp.setMessage("supplierCode is required");
                resp.setData(null);
                return resp;
            }
            var opt = supplierRepository.findBySupplierCode(supplierCode);
            if (opt.isEmpty() || !Boolean.TRUE.equals(opt.get().getStatus())) {
                resp.setStatusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
                resp.setMessage("Supplier not found or inactive");
                resp.setData(null);
                return resp;
            }

            String sql = "SELECT sp.product_code, sp.import_price, sp.status AS sp_status FROM supplier_product sp WHERE sp.supplier_code = ?";
            List<com.devsoga.BookStore_V2.dtos.responses.ProductRespone> result = new java.util.ArrayList<>();
            var rows = jdbcTemplate.queryForList(sql, new Object[]{supplierCode});
            for (Object rowObj : rows) {
                if (!(rowObj instanceof java.util.Map)) continue;
                java.util.Map<?,?> row = (java.util.Map<?,?>) rowObj;
                Object pc = row.get("product_code");
                Object ip = row.get("import_price");
                // supplier_product status value is available as 'sp_status' if needed
                if (pc == null) continue;
                String productCode = pc.toString();
                var optProd = productRepository.findByProductCode(productCode);
                if (optProd.isEmpty()) continue;
                var prodEntity = optProd.get();
                if (!Boolean.TRUE.equals(prodEntity.getStatus())) continue;
                com.devsoga.BookStore_V2.dtos.responses.ProductRespone pr = productService.toResp(prodEntity);
                try {
                    if (ip != null) {
                        java.math.BigDecimal importPrice = new java.math.BigDecimal(ip.toString());
                        pr.setImportPrice(importPrice);
                    }
                } catch (Exception ex) {
                    // ignore parse errors
                }
                result.add(pr);
            }

            resp.setStatusCode(org.springframework.http.HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(result);
        } catch (Exception e) {
            resp.setStatusCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private SupplierResponse toResp(SupplierEntity e) {
        if (e == null) return null;
        SupplierResponse r = new SupplierResponse();
        r.setId(e.getId());
        r.setSupplierCode(e.getSupplierCode());
        r.setSupplierName(e.getSupplierName());
        r.setAddress(e.getAddress());
        r.setDescription(e.getDescription());
        r.setPhoneNumber(e.getPhoneNumber());
        r.setEmail(e.getEmail());
        r.setStatus(e.getStatus());

        // populate products provided by this supplier (SupplierProductResponse list)
        try {
                java.util.List<com.devsoga.BookStore_V2.enties.SupplierProductEntity> spList =
                    supplierProductRepository.findBySupplierCode(e.getSupplierCode());
            java.util.List<com.devsoga.BookStore_V2.dtos.responses.SupplierProductResponse> products = new java.util.ArrayList<>();
            // Collect product codes and batch fetch product info
            java.util.List<String> codes = new java.util.ArrayList<>();
            for (com.devsoga.BookStore_V2.enties.SupplierProductEntity sp : spList) {
                if (sp.getProductCode() != null) codes.add(sp.getProductCode());
            }
            java.util.Map<String, com.devsoga.BookStore_V2.enties.ProductEntity> prodMap = new java.util.HashMap<>();
            if (!codes.isEmpty()) {
                java.util.List<com.devsoga.BookStore_V2.enties.ProductEntity> prods = productRepository.findByProductCodeIn(codes);
                for (com.devsoga.BookStore_V2.enties.ProductEntity p : prods) prodMap.put(p.getProductCode(), p);
            }

            for (com.devsoga.BookStore_V2.enties.SupplierProductEntity sp : spList) {
                com.devsoga.BookStore_V2.dtos.responses.SupplierProductResponse spr = new com.devsoga.BookStore_V2.dtos.responses.SupplierProductResponse();
                spr.setId(sp.getId());
                spr.setSupplierProductCode(sp.getSupplierProductCode());
                spr.setSupplierCode(sp.getSupplierCode());
                spr.setProductCode(sp.getProductCode());
                spr.setImportPrice(sp.getImportPrice());
                spr.setStatus(sp.getStatus());
                spr.setCreatedDate(sp.getCreatedDate());
                var prod = prodMap.get(sp.getProductCode());
                if (prod != null) {
                    spr.setProductName(prod.getProductName());
                    spr.setImage(prod.getImage());
                    if (prod.getProductCategoryEntity() != null) {
                        spr.setCategoryName(prod.getProductCategoryEntity().getCategoryName());
                    }
                }
                products.add(spr);
            }
            r.setProductProvide(products);
        } catch (Exception ex) {
            r.setProductProvide(null);
        }

        return r;
    }
}
