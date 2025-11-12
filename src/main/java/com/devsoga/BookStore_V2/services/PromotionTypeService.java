package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.PromotionTypeRequest;
import com.devsoga.BookStore_V2.dtos.responses.PromotionTypeResponse;
import com.devsoga.BookStore_V2.enties.PromotionTypeEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.PromotionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionTypeService {

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

    public BaseRespone create(PromotionTypeRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getPromotionTypeName() == null || req.getPromotionTypeName().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("promotionTypeName is required");
                resp.setData(null);
                return resp;
            }

            String code = req.getPromotionTypeCode();
            if (code == null || code.isBlank()) {
                code = "PT_" + System.currentTimeMillis();
            } else {
                if (promotionTypeRepository.findByPromotionTypeCode(code).isPresent()) {
                    resp.setStatusCode(HttpStatus.CONFLICT.value());
                    resp.setMessage("promotionTypeCode already exists");
                    resp.setData(null);
                    return resp;
                }
            }

            PromotionTypeEntity e = new PromotionTypeEntity();
            e.setPromotionTypeCode(code);
            e.setPromotionTypeName(req.getPromotionTypeName());
            e.setDescription(req.getDescription());
            PromotionTypeEntity saved = promotionTypeRepository.save(e);

            PromotionTypeResponse dto = toResp(saved);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(dto);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create promotion type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<PromotionTypeEntity> list = promotionTypeRepository.findAll();
            List<PromotionTypeResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch promotion types: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCode(String code) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = promotionTypeRepository.findByPromotionTypeCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Promotion type not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch promotion type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String code, PromotionTypeRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = promotionTypeRepository.findByPromotionTypeCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Promotion type not found");
                resp.setData(null);
                return resp;
            }
            PromotionTypeEntity e = opt.get();
            if (req.getPromotionTypeCode() != null && !req.getPromotionTypeCode().isBlank()) e.setPromotionTypeCode(req.getPromotionTypeCode());
            if (req.getPromotionTypeName() != null) e.setPromotionTypeName(req.getPromotionTypeName());
            if (req.getDescription() != null) e.setDescription(req.getDescription());
            PromotionTypeEntity saved = promotionTypeRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update promotion type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String code) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = promotionTypeRepository.findByPromotionTypeCode(code);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Promotion type not found");
                resp.setData(null);
                return resp;
            }
            promotionTypeRepository.delete(opt.get());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete promotion type: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private PromotionTypeResponse toResp(PromotionTypeEntity e) {
        if (e == null) return null;
        PromotionTypeResponse r = new PromotionTypeResponse();
        r.setId(e.getId());
        r.setPromotionTypeCode(e.getPromotionTypeCode());
        r.setPromotionTypeName(e.getPromotionTypeName());
        r.setDescription(e.getDescription());
        return r;
    }
}
