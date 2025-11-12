package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.CategoryRequest;
import com.devsoga.BookStore_V2.dtos.responses.CategoryRespone;
import com.devsoga.BookStore_V2.enties.ProductCategoryEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public BaseRespone createCategory(CategoryRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req.getCategoryName() == null || req.getCategoryName().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("categoryName is required");
                resp.setData(null);
                return resp;
            }

            String code = req.getCategoryCode();
            if (code == null || code.isBlank()) {
                code = "CAT_" + System.currentTimeMillis();
            } else {
                // check exists
                if (categoryRepository.findByCategoryCode(code).isPresent()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("categoryCode already exists");
                    resp.setData(null);
                    return resp;
                }
            }

            ProductCategoryEntity e = new ProductCategoryEntity();
            e.setCategoryCode(code);
            e.setCategoryName(req.getCategoryName());
            if (req.getCategoryType() != null) e.setCategoryType(req.getCategoryType());
            e.setDescription(req.getDescription());
            ProductCategoryEntity saved = categoryRepository.save(e);

            CategoryRespone dto = toResp(saved);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(dto);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create category: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getAllCategories() {
        BaseRespone resp = new BaseRespone();
        try {
            List<ProductCategoryEntity> list = categoryRepository.findAll();
            List<CategoryRespone> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch categories: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getCategoryByCode(String categoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            ProductCategoryEntity e = categoryRepository.findByCategoryCode(categoryCode).orElse(null);
            if (e == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Category not found");
                resp.setData(null);
            } else {
                resp.setStatusCode(HttpStatus.OK.value());
                resp.setMessage("Success");
                resp.setData(toResp(e));
            }
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch category: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone updateCategory(String categoryCode, CategoryRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            ProductCategoryEntity e = categoryRepository.findByCategoryCode(categoryCode).orElse(null);
            if (e == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Category not found");
                resp.setData(null);
                return resp;
            }
            if (req.getCategoryName() != null) e.setCategoryName(req.getCategoryName());
            if (req.getCategoryType() != null) e.setCategoryType(req.getCategoryType());
            if (req.getDescription() != null) e.setDescription(req.getDescription());
            ProductCategoryEntity saved = categoryRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update category: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteCategory(String categoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            ProductCategoryEntity e = categoryRepository.findByCategoryCode(categoryCode).orElse(null);
            if (e == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Category not found");
                resp.setData(null);
                return resp;
            }
            categoryRepository.delete(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete category: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private CategoryRespone toResp(ProductCategoryEntity e) {
        if (e == null) return null;
        CategoryRespone r = new CategoryRespone();
        r.setCategoryCode(e.getCategoryCode());
        r.setCategoryName(e.getCategoryName());
        r.setCategoryType(e.getCategoryType());
        r.setDescription(e.getDescription());
        return r;
    }
}
