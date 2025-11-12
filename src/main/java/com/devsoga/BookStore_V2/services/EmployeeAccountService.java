package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.EmployeeAccountRequest;
import com.devsoga.BookStore_V2.dtos.responses.AccountRespone;
import com.devsoga.BookStore_V2.enties.AccountEntity;
import com.devsoga.BookStore_V2.enties.EmployeeEntity;
import com.devsoga.BookStore_V2.enties.RoleEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.AccountRepository;
import com.devsoga.BookStore_V2.repositories.EmployeeRepository;
import com.devsoga.BookStore_V2.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BaseRespone getAllEmployeeAccounts() {
        BaseRespone resp = new BaseRespone();
        try {
            List<AccountEntity> all = accountRepository.findAll();
            // filter accounts that have employee list
            List<AccountEntity> employeeAccounts = all.stream()
                    .filter(a -> a.getEmployeeList() != null && !a.getEmployeeList().isEmpty())
                    .collect(Collectors.toList());
            List<AccountRespone> data = employeeAccounts.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch employee accounts: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByEmployeeCode(String employeeCode) {
        BaseRespone resp = new BaseRespone();
        try {
            EmployeeEntity emp = employeeRepository.findByEmployeeCode(employeeCode).orElse(null);
            if (emp == null || emp.getAccountEntity() == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Employee account not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(emp.getAccountEntity()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch employee account: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone createEmployeeAccount(EmployeeAccountRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req.getUsername() == null || req.getUsername().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("username is required");
                resp.setData(null);
                return resp;
            }
            // check username uniqueness
            List<AccountEntity> existing = accountRepository.findByUsername(req.getUsername());
            if (existing != null && !existing.isEmpty()) {
                resp.setStatusCode(HttpStatus.CONFLICT.value());
                resp.setMessage("Username already exists");
                resp.setData(null);
                return resp;
            }

            AccountEntity account = new AccountEntity();
            account.setUsername(req.getUsername());
            if (req.getPassword() != null && !req.getPassword().isBlank()) {
                account.setPassword(passwordEncoder.encode(req.getPassword()));
            }
            account.setEmail(req.getEmail());
            account.setPhoneNumber(null);
            account.setStatus(true);

            String acctCode = req.getAccountCode();
            if (acctCode == null || acctCode.isBlank()) {
                acctCode = "AC_" + System.currentTimeMillis()%100000; // short
            }
            account.setAccountCode(acctCode);

            String roleCode = req.getRoleCode();
            if (roleCode == null || roleCode.isBlank()) roleCode = "EMP";
            RoleEntity role = roleRepository.findByRoleCode(roleCode).orElse(null);
            account.setRoleEntity(role);

            AccountEntity saved = accountRepository.save(account);

            // create employee record
            EmployeeEntity emp = new EmployeeEntity();
            String empCode = req.getEmployeeCode();
            if (empCode == null || empCode.isBlank()) empCode = "EMP_" + System.currentTimeMillis()%100000;
            emp.setEmployeeCode(empCode);
            emp.setEmployeeName(req.getEmployeeName() != null ? req.getEmployeeName() : req.getUsername());
            if (req.getBirthDate() != null && !req.getBirthDate().isBlank()) {
                emp.setBirthDate(LocalDate.parse(req.getBirthDate()));
            }
            emp.setGender(req.getGender());
            emp.setAccountEntity(saved);
            employeeRepository.save(emp);

            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Employee account created");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create employee account: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone updateEmployeeAccount(String accountCode, EmployeeAccountRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            AccountEntity acc = accountRepository.findAll().stream()
                    .filter(a -> accountCode.equals(a.getAccountCode()))
                    .findFirst().orElse(null);
            if (acc == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Account not found");
                resp.setData(null);
                return resp;
            }
            if (req.getPassword() != null && !req.getPassword().isBlank()) {
                acc.setPassword(passwordEncoder.encode(req.getPassword()));
            }
            if (req.getEmail() != null) acc.setEmail(req.getEmail());
            if (req.getRoleCode() != null) {
                RoleEntity r = roleRepository.findByRoleCode(req.getRoleCode()).orElse(null);
                acc.setRoleEntity(r);
            }
            AccountEntity saved = accountRepository.save(acc);

            // update employee info if provided
            if (saved.getEmployeeList() != null && !saved.getEmployeeList().isEmpty()) {
                EmployeeEntity emp = saved.getEmployeeList().get(0);
                if (req.getEmployeeName() != null) emp.setEmployeeName(req.getEmployeeName());
                if (req.getBirthDate() != null && !req.getBirthDate().isBlank()) emp.setBirthDate(LocalDate.parse(req.getBirthDate()));
                if (req.getGender() != null) emp.setGender(req.getGender());
                employeeRepository.save(emp);
            }

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update employee account: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteEmployeeAccount(String accountCode) {
        BaseRespone resp = new BaseRespone();
        try {
            AccountEntity acc = accountRepository.findAll().stream()
                    .filter(a -> accountCode.equals(a.getAccountCode()))
                    .findFirst().orElse(null);
            if (acc == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Account not found");
                resp.setData(null);
                return resp;
            }
            // soft delete: set status false
            acc.setStatus(Boolean.FALSE);
            accountRepository.save(acc);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete employee account: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private AccountRespone toResp(AccountEntity acct) {
        AccountRespone dto = new AccountRespone();
        if (acct == null) return dto;
        dto.setAccountCode(acct.getAccountCode());
        dto.setUsername(acct.getUsername());
        dto.setEmail(acct.getEmail());
        dto.setRoleCode(acct.getRoleEntity() != null ? acct.getRoleEntity().getRoleCode() : null);
        if (acct.getEmployeeList() != null && !acct.getEmployeeList().isEmpty()) {
            EmployeeEntity emp = acct.getEmployeeList().get(0);
            dto.setEmployeeCode(emp.getEmployeeCode());
            dto.setEmployeeName(emp.getEmployeeName());
        }
        return dto;
    }
}
