package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.CouponRequest;
import com.devsoga.BookStore_V2.dtos.responses.CouponRespone;
import com.devsoga.BookStore_V2.enties.CouponEntity;
import com.devsoga.BookStore_V2.enties.PromotionTypeEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.CouponRepository;
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
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private PromotionTypeRepository promotionTypeRepository;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<CouponEntity> list = couponRepository.findAll();
            List<CouponRespone> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch coupons: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCode(String couponCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = couponRepository.findByCouponCode(couponCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Coupon not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch coupon: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone create(CouponRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getCouponName() == null || req.getCouponName().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("couponName is required");
                resp.setData(null);
                return resp;
            }

            String code = req.getCouponCode();
            if (code == null || code.isBlank()) {
                code = "COUP_" + System.currentTimeMillis();
            } else {
                if (couponRepository.findByCouponCode(code).isPresent()) {
                    resp.setStatusCode(HttpStatus.CONFLICT.value());
                    resp.setMessage("couponCode already exists");
                    resp.setData(null);
                    return resp;
                }
            }

            CouponEntity e = new CouponEntity();
            e.setCouponCode(code);
            e.setCouponName(req.getCouponName());
            e.setDescription(req.getDescription());
            e.setValue(req.getValue() == null ? BigDecimal.ZERO : req.getValue());
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

            if (req.getStartDate() != null && !req.getStartDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getStartDate());
                    e.setStartDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {}
            }
            if (req.getEndDate() != null && !req.getEndDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getEndDate());
                    e.setEndDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {}
            }

            CouponEntity saved = couponRepository.save(e);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create coupon: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String couponCode, CouponRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = couponRepository.findByCouponCode(couponCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Coupon not found");
                resp.setData(null);
                return resp;
            }
            CouponEntity e = opt.get();
            if (req.getCouponCode() != null && !req.getCouponCode().isBlank()) e.setCouponCode(req.getCouponCode());
            if (req.getCouponName() != null) e.setCouponName(req.getCouponName());
            if (req.getDescription() != null) e.setDescription(req.getDescription());
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
                } catch (Exception ignored) {}
            }
            if (req.getEndDate() != null && !req.getEndDate().isBlank()) {
                try {
                    LocalDateTime t = LocalDateTime.parse(req.getEndDate());
                    e.setEndDate(Timestamp.valueOf(t));
                } catch (Exception ignored) {}
            }

            CouponEntity saved = couponRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update coupon: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String couponCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = couponRepository.findByCouponCode(couponCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Coupon not found");
                resp.setData(null);
                return resp;
            }
            CouponEntity e = opt.get();
            e.setStatus(Boolean.FALSE);
            couponRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted (soft)");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete coupon: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private CouponRespone toResp(CouponEntity e) {
        if (e == null) return null;
        CouponRespone r = new CouponRespone();
        r.setId(e.getId());
        r.setCouponCode(e.getCouponCode());
        r.setCouponName(e.getCouponName());
        r.setDescription(e.getDescription());
        r.setValue(e.getValue());
        r.setStartDate(e.getStartDate());
        r.setEndDate(e.getEndDate());
        r.setStatus(e.getStatus());
        // Note: used and used_at fields removed as they don't exist in database schema
        if (e.getPromotionTypeEntity() != null) {
            r.setPromotionTypeCode(e.getPromotionTypeEntity().getPromotionTypeCode());
            r.setPromotionTypeName(e.getPromotionTypeEntity().getPromotionTypeName());
        }
        return r;
    }
}
