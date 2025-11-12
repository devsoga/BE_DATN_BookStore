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

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
        List<SupplierEntity> list = supplierRepository.findAll().stream()
            .filter(s -> Boolean.TRUE.equals(s.getStatus()))
            .collect(Collectors.toList());
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
        return r;
    }
}
