package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.PromotionRequest;
import com.devsoga.BookStore_V2.dtos.responses.PromotionResponse;
import com.devsoga.BookStore_V2.enties.PromotionEntity;
import com.devsoga.BookStore_V2.enties.PromotionTypeEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.PromotionRepository;
import com.devsoga.BookStore_V2.repositories.PromotionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<PromotionEntity> list = promotionRepository.findAll();
            List<PromotionResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch promotions: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCode(String promotionCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = promotionRepository.findByPromotionCode(promotionCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Promotion not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch promotion: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone create(PromotionRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getPromotionName() == null || req.getPromotionName().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("promotionName is required");
                resp.setData(null);
                return resp;
            }

            String code = req.getPromotionCode();
            if (code == null || code.isBlank()) {
                code = "PROMO_" + System.currentTimeMillis();
            } else {
                if (promotionRepository.findByPromotionCode(code).isPresent()) {
                    resp.setStatusCode(HttpStatus.CONFLICT.value());
                    resp.setMessage("promotionCode already exists");
                    resp.setData(null);
                    return resp;
                }
            }

            PromotionEntity e = new PromotionEntity();
            e.setPromotionCode(code);
            e.setPromotionName(req.getPromotionName());
            e.setValue(req.getValue() == null ? null : req.getValue());
            e.setStatus(req.getStatus() == null ? Boolean.TRUE : req.getStatus());

            if (req.getPromotionTypeCode() != null && !req.getPromotionTypeCode().isBlank()) {
                PromotionTypeEntity pt = promotionTypeRepository.findByPromotionTypeCode(req.getPromotionTypeCode()).orElse(null);
                if (pt == null) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("promotionTypeCode not found");
                    resp.setData(null);
                    return resp;
                }
                e.setPromotionTypeEntity(pt);
            }

            // parse dates if provided (expects ISO_LOCAL_DATE_TIME)
            if (req.getStartDate() != null && !req.getStartDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getStartDate());
                    e.setStartDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {
                }
            }
            if (req.getEndDate() != null && !req.getEndDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getEndDate());
                    e.setEndDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {
                }
            }

            PromotionEntity saved = promotionRepository.save(e);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create promotion: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String promotionCode, PromotionRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = promotionRepository.findByPromotionCode(promotionCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Promotion not found");
                resp.setData(null);
                return resp;
            }
            PromotionEntity e = opt.get();
            if (req.getPromotionCode() != null && !req.getPromotionCode().isBlank()) e.setPromotionCode(req.getPromotionCode());
            if (req.getPromotionName() != null) e.setPromotionName(req.getPromotionName());
            if (req.getValue() != null) e.setValue(req.getValue());
            if (req.getStatus() != null) e.setStatus(req.getStatus());

            if (req.getPromotionTypeCode() != null) {
                PromotionTypeEntity pt = promotionTypeRepository.findByPromotionTypeCode(req.getPromotionTypeCode()).orElse(null);
                if (pt == null) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("promotionTypeCode not found");
                    resp.setData(null);
                    return resp;
                }
                e.setPromotionTypeEntity(pt);
            }

            if (req.getStartDate() != null && !req.getStartDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getStartDate());
                    e.setStartDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {
                }
            }
            if (req.getEndDate() != null && !req.getEndDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getEndDate());
                    e.setEndDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {
                }
            }

            PromotionEntity saved = promotionRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update promotion: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String promotionCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = promotionRepository.findByPromotionCode(promotionCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Promotion not found");
                resp.setData(null);
                return resp;
            }
            PromotionEntity e = opt.get();
            // soft delete by setting status = false
            e.setStatus(Boolean.FALSE);
            promotionRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted (soft)");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete promotion: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private PromotionResponse toResp(PromotionEntity e) {
        if (e == null) return null;
        PromotionResponse r = new PromotionResponse();
        r.setId(e.getId());
        r.setPromotionCode(e.getPromotionCode());
        r.setPromotionName(e.getPromotionName());
        r.setValue(e.getValue());
        r.setStartDate(e.getStartDate());
        r.setEndDate(e.getEndDate());
        r.setStatus(e.getStatus());
        if (e.getPromotionTypeEntity() != null) {
            r.setPromotionTypeCode(e.getPromotionTypeEntity().getPromotionTypeCode());
            r.setPromotionTypeName(e.getPromotionTypeEntity().getPromotionTypeName());
        }
        return r;
    }
}
