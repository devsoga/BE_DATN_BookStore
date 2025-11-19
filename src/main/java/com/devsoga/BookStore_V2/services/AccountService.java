package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.RegisterRequest;
import com.devsoga.BookStore_V2.dtos.responses.AccountRespone;
import com.devsoga.BookStore_V2.enties.AccountEntity;
import com.devsoga.BookStore_V2.repositories.AccountRepository;
import com.devsoga.BookStore_V2.repositories.RoleRepository;
import com.devsoga.BookStore_V2.enties.RoleEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.PromotionRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PromotionRepository promotionRepository;
    
    @Autowired
    private com.devsoga.BookStore_V2.repositories.CustomerRepository customerRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.EmployeeRepository employeeRepository;




    public BaseRespone getAccountDetails(String username) {
        BaseRespone response = new BaseRespone();
        // Implementation to retrieve account details by username
        // This is a placeholder implementation
        List<AccountEntity> listAccount = accountRepository.findByUsername(username);

        if (!listAccount.isEmpty()) {
            AccountEntity acct = listAccount.get(0);
            AccountRespone dto = new AccountRespone();
            dto.setAccountCode(acct.getAccountCode());
            dto.setUsername(acct.getUsername());
            dto.setEmail(acct.getEmail());
            String roleCode = acct.getRoleEntity() != null ? acct.getRoleEntity().getRoleCode() : null;
            dto.setRoleCode(roleCode);
            // only return customerCode when role is USER
            if ("USER".equalsIgnoreCase(roleCode)) {
                String customerCode = null;
                if (acct.getCustomerList() != null && !acct.getCustomerList().isEmpty()) {
                    var customer = acct.getCustomerList().get(0);
                    customerCode = customer.getCustomerCode();
                    dto.setCustomerName(customer.getCustomerName());
                        // populate points from customer
                        try {
                            if (customer.getPoints() != null) dto.setPoints(java.math.BigDecimal.valueOf(customer.getPoints()));
                        } catch (Exception ex) {
                            // ignore
                        }
                    // expose promotion_code if linked via customer type
                    if (customer.getCustomerTypeEntity() != null) {
var ct = customer.getCustomerTypeEntity();
                        dto.setCustomerTypeCode(ct.getCustomerTypeCode());
                        dto.setCustomerTypeName(ct.getCustomerTypeName());
                        if (ct.getPromotionCode() != null && !ct.getPromotionCode().isBlank()) {
                            dto.setPromotion_code(ct.getPromotionCode());
                            try {
                                var promo = promotionRepository.findByPromotionCode(ct.getPromotionCode()).orElse(null);
                                if (promo != null && promo.getValue() != null) {
                                    dto.setMemberDiscount(promo.getValue());
                                }
                            } catch (Exception ex) {
                                // ignore and leave memberDiscount null
                            }
                        }
                    }
                }
                dto.setCustomerCode(customerCode);
            } else {
                dto.setCustomerCode(null);
            }

            response.setStatusCode(200);
            response.setMessage("Account found");
            response.setData(dto);
        } else {
            throw new RuntimeException("Account not found");
        }
        return response;
    }

    public BaseRespone getAccountDetailsByPhone(String phoneNumber) {
        BaseRespone response = new BaseRespone();
        List<AccountEntity> listAccount = accountRepository.findByPhoneNumber(phoneNumber);
        if (listAccount != null && !listAccount.isEmpty()) {
            AccountEntity acct = listAccount.get(0);
            AccountRespone dto = new AccountRespone();
            dto.setAccountCode(acct.getAccountCode());
            dto.setUsername(acct.getUsername());
            dto.setEmail(acct.getEmail());
            String roleCode = acct.getRoleEntity() != null ? acct.getRoleEntity().getRoleCode() : null;
            dto.setRoleCode(roleCode);
            // only return customerCode when role is USER
            if ("USER".equalsIgnoreCase(roleCode)) {
                String customerCode = null;
                if (acct.getCustomerList() != null && !acct.getCustomerList().isEmpty()) {
                    var customer = acct.getCustomerList().get(0);
                    customerCode = customer.getCustomerCode();
                    dto.setCustomerName(customer.getCustomerName());
                        // populate points from customer
                        try {
                            if (customer.getPoints() != null) dto.setPoints(java.math.BigDecimal.valueOf(customer.getPoints()));
                        } catch (Exception ex) {
                            // ignore
                        }
                        // populate points from customer
                        try {
                            if (customer.getPoints() != null) dto.setPoints(java.math.BigDecimal.valueOf(customer.getPoints()));
} catch (Exception ex) {
                            // ignore
                        }
                    if (customer.getCustomerTypeEntity() != null) {
                        var ct = customer.getCustomerTypeEntity();
                        dto.setCustomerTypeCode(ct.getCustomerTypeCode());
                        dto.setCustomerTypeName(ct.getCustomerTypeName());
                        if (ct.getPromotionCode() != null && !ct.getPromotionCode().isBlank()) {
                            dto.setPromotion_code(ct.getPromotionCode());
                            try {
                                var promo = promotionRepository.findByPromotionCode(ct.getPromotionCode()).orElse(null);
                                if (promo != null && promo.getValue() != null) {
                                    dto.setMemberDiscount(promo.getValue());
                                }
                            } catch (Exception ex) {
                                // ignore
                            }
                        }
                    }
                }
                dto.setCustomerCode(customerCode);
            } else {
                dto.setCustomerCode(null);
            }

            // include phone_number on response by placing into email field if email null? better to set username/email remain as-is
            response.setStatusCode(200);
            response.setMessage("Account found by phone");
            response.setData(dto);
        } else {
            response.setStatusCode(404);
            response.setMessage("Account not found for phone: " + phoneNumber);
            response.setData(null);
        }
        return response;
    }

    public BaseRespone createAccount(RegisterRequest request) {
        // check for duplicate username
        List<AccountEntity> existing = accountRepository.findByUsername(request.getUsername());
        if (existing != null && !existing.isEmpty()) {
           throw new RuntimeException("Account already exists");
        }
        // create and save the account
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(request.getUsername());
        String rawPassword = request.getPassword();
        if (rawPassword != null && !rawPassword.isBlank()) {
            accountEntity.setPassword(passwordEncoder.encode(rawPassword));
        } else {
            accountEntity.setPassword(null);
        }
        accountEntity.setEmail(request.getEmail());

        // generate account code if not provided
        String acctCode = request.getAccountCode();
        if (acctCode == null || acctCode.isBlank()) {
            long epoch = System.currentTimeMillis() / 1000L;
            int rnd = (int) (Math.random() * 900) + 100;
            acctCode = "AC_" + epoch + rnd;
        }
        accountEntity.setAccountCode(acctCode);

        // determine role: default to USER when not provided
        String roleCode = request.getRoleCode();
if (roleCode == null || roleCode.isBlank()) {
            roleCode = "USER";
        }
        RoleEntity role = roleRepository.findByRoleCode(roleCode).orElse(null);
        accountEntity.setRoleEntity(role);
        AccountEntity saved = accountRepository.save(accountEntity);

        // create related customer or employee depending on role
        if ("USER".equalsIgnoreCase(roleCode)) {
            // create customer
            com.devsoga.BookStore_V2.enties.CustomerEntity cust = new com.devsoga.BookStore_V2.enties.CustomerEntity();
            // generate customer code
            long epoch = System.currentTimeMillis() / 1000L;
            int rnd = (int) (Math.random() * 900) + 100;
            String custCode = "CUS_" + epoch + rnd;
            cust.setCustomerCode(custCode);
            cust.setCustomerName(request.getUsername());
            cust.setAccountEntity(saved);
            customerRepository.save(cust);
        } else {
            // create employee for other roles
            com.devsoga.BookStore_V2.enties.EmployeeEntity emp = new com.devsoga.BookStore_V2.enties.EmployeeEntity();
            long epoch2 = System.currentTimeMillis() / 1000L;
            int rnd2 = (int) (Math.random() * 900) + 100;
            String empCode = "EMP_" + epoch2 + rnd2;
            emp.setEmployeeCode(empCode);
            emp.setEmployeeName(request.getUsername());
            emp.setAccountEntity(saved);
            employeeRepository.save(emp);
        }

        BaseRespone resp = getAccountDetails(saved.getUsername());
        // override message and status for creation
        resp.setStatusCode(201);
        resp.setMessage("Account created successfully");
        return resp;
    }
}