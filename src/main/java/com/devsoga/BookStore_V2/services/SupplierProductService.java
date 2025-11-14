package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.SupplierProductRequest;
import com.devsoga.BookStore_V2.dtos.responses.SupplierProductResponse;
import com.devsoga.BookStore_V2.enties.SupplierProductEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.SupplierProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierProductService {

    @Autowired
    private SupplierProductRepository supplierProductRepository;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<SupplierProductEntity> list = supplierProductRepository.findAll();
            List<SupplierProductResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
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

    public BaseRespone getByCode(String code) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = supplierProductRepository.findBySupplierProductCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone create(SupplierProductRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getSupplierProductCode() == null || req.getSupplierProductCode().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("supplierProductCode is required");
                resp.setData(null);
                return resp;
            }
            if (supplierProductRepository.findBySupplierProductCode(req.getSupplierProductCode()).isPresent()) {
                resp.setStatusCode(HttpStatus.CONFLICT.value());
                resp.setMessage("Already exists");
                resp.setData(null);
                return resp;
            }
            SupplierProductEntity e = new SupplierProductEntity();
            e.setSupplierProductCode(req.getSupplierProductCode());
            e.setSupplierCode(req.getSupplierCode());
            e.setProductCode(req.getProductCode());
            e.setImportPrice(req.getImportPrice());
            e.setStatus(req.getStatus() == null ? Boolean.TRUE : req.getStatus());
            var saved = supplierProductRepository.save(e);
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

    public BaseRespone update(String supplierProductCode, SupplierProductRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = supplierProductRepository.findBySupplierProductCode(supplierProductCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            var e = opt.get();
            if (req.getSupplierProductCode() != null && !req.getSupplierProductCode().isBlank()) e.setSupplierProductCode(req.getSupplierProductCode());
            if (req.getSupplierCode() != null) e.setSupplierCode(req.getSupplierCode());
            if (req.getProductCode() != null) e.setProductCode(req.getProductCode());
            if (req.getImportPrice() != null) e.setImportPrice(req.getImportPrice());
            if (req.getStatus() != null) e.setStatus(req.getStatus());
            var saved = supplierProductRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String supplierProductCode, boolean hard) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = supplierProductRepository.findBySupplierProductCode(supplierProductCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            var e = opt.get();
            if (hard) {
                supplierProductRepository.delete(e);
                resp.setMessage("Deleted (hard)");
            } else {
                e.setStatus(Boolean.FALSE);
                supplierProductRepository.save(e);
                resp.setMessage("Deleted (soft)");
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private SupplierProductResponse toResp(SupplierProductEntity e) {
        if (e == null) return null;
        SupplierProductResponse r = new SupplierProductResponse();
        r.setId(e.getId());
        r.setSupplierProductCode(e.getSupplierProductCode());
        r.setSupplierCode(e.getSupplierCode());
        r.setProductCode(e.getProductCode());
        r.setImportPrice(e.getImportPrice());
        r.setStatus(e.getStatus());
        r.setCreatedDate(e.getCreatedDate());
        return r;
    }
}
