package com.devsoga.BookStore_V2.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.devsoga.BookStore_V2.dtos.requests.LoginRequest;
import com.devsoga.BookStore_V2.dtos.responses.AccountRespone;
import com.devsoga.BookStore_V2.enties.AccountEntity;
import com.devsoga.BookStore_V2.enties.PromotionEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.PromotionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PromotionRepository promotionRepository;

    public BaseRespone login(LoginRequest request) {
         UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        // Gọi Spring Security để xác thực
        Authentication authentication = authenticationManager.authenticate(authToken);
        
        AccountEntity account = (AccountEntity) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);

        // build DTO
        AccountRespone dto = new AccountRespone();
        dto.setAccountCode(account.getAccountCode());
        dto.setUsername(account.getUsername());
        dto.setEmail(account.getEmail());
        String roleCode = account.getRoleEntity() != null ? account.getRoleEntity().getRoleCode() : null;
        dto.setRoleCode(roleCode);
        if ("USER".equalsIgnoreCase(roleCode)) {
            String customerCode = null;
            if (account.getCustomerList() != null && !account.getCustomerList().isEmpty()) {
                var customer = account.getCustomerList().get(0);
                customerCode = customer.getCustomerCode();
                dto.setCustomerName(customer.getCustomerName());

                // Customer type info
                if (customer.getCustomerTypeEntity() != null) {
                    var ct = customer.getCustomerTypeEntity();
                    dto.setCustomerTypeCode(ct.getCustomerTypeCode());
                    dto.setCustomerTypeName(ct.getCustomerTypeName());

                    // If customer type has linked promotion_code, fetch promotion value
                    String promoCode = ct.getPromotionCode();
                    if (promoCode != null && !promoCode.isBlank()) {
                        PromotionEntity promo = promotionRepository.findByPromotionCode(promoCode)
                                .orElse(null);
                        if (promo != null && promo.getValue() != null) {
                            dto.setMemberDiscount(promo.getValue());
                        }
                        // also expose promotion_code on account response for USER
                        dto.setPromotion_code(promoCode);
                    }
                }
            }
            dto.setCustomerCode(customerCode);
        } else {
            dto.setCustomerCode(null);
        }
        dto.setAccessToken(accessToken);
        dto.setRefreshToken(refreshToken);

        // wrap into BaseRespone
        BaseRespone response = new BaseRespone();
        response.setStatusCode(200);
        response.setMessage("Login successful");
        response.setData(dto);
        return response;
    }
}
