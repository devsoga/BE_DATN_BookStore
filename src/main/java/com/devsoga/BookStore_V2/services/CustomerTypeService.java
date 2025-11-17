package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.CustomerTypeRequest;
import com.devsoga.BookStore_V2.dtos.responses.CustomerTypeResponse;
import com.devsoga.BookStore_V2.enties.CustomerTypeEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    public BaseRespone create(CustomerTypeRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getCustomerTypeName() == null || req.getCustomerTypeName().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("customerTypeName is required");
                resp.setData(null);
                return resp;
            }

            String code = req.getCustomerTypeCode();
            if (code == null || code.isBlank()) {
                code = "CT_" + System.currentTimeMillis();
            } else {
                if (customerTypeRepository.findByCustomerTypeCode(code).isPresent()) {
                    resp.setStatusCode(HttpStatus.CONFLICT.value());
                    resp.setMessage("customerTypeCode already exists");
                    resp.setData(null);
                    return resp;
                }
            }

            CustomerTypeEntity e = new CustomerTypeEntity();
            e.setCustomerTypeCode(code);
            e.setCustomerTypeName(req.getCustomerTypeName());
            e.setPromotionCode(req.getPromotionCode());
            e.setDescription(req.getDescription());
            CustomerTypeEntity saved = customerTypeRepository.save(e);

            CustomerTypeResponse dto = toResp(saved);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(dto);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create customer type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<CustomerTypeEntity> list = customerTypeRepository.findAll();
            List<CustomerTypeResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch customer types: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCode(String code) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = customerTypeRepository.findByCustomerTypeCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Customer type not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch customer type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String code, CustomerTypeRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = customerTypeRepository.findByCustomerTypeCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Customer type not found");
                resp.setData(null);
                return resp;
            }
            CustomerTypeEntity e = opt.get();
            if (req.getCustomerTypeCode() != null && !req.getCustomerTypeCode().isBlank()) e.setCustomerTypeCode(req.getCustomerTypeCode());
            if (req.getCustomerTypeName() != null) e.setCustomerTypeName(req.getCustomerTypeName());
            if (req.getPromotionCode() != null) e.setPromotionCode(req.getPromotionCode());
            if (req.getDescription() != null) e.setDescription(req.getDescription());
            CustomerTypeEntity saved = customerTypeRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update customer type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String code) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = customerTypeRepository.findByCustomerTypeCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Customer type not found");
                resp.setData(null);
                return resp;
            }
            customerTypeRepository.delete(opt.get());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete customer type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private CustomerTypeResponse toResp(CustomerTypeEntity e) {
        if (e == null) return null;
        CustomerTypeResponse r = new CustomerTypeResponse();
        r.setId(e.getId());
        r.setCustomerTypeCode(e.getCustomerTypeCode());
        r.setCustomerTypeName(e.getCustomerTypeName());
        r.setPromotionCode(e.getPromotionCode());
        r.setDescription(e.getDescription());
        return r;
    }
}
