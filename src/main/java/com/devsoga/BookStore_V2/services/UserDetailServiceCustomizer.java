package com.devsoga.BookStore_V2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsoga.BookStore_V2.enties.AccountEntity;
import com.devsoga.BookStore_V2.repositories.AccountRepository;


@Service
public class UserDetailServiceCustomizer implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<AccountEntity> accounts = accountRepository.findByUsername(username);
        if (accounts.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return accounts.get(0);
    }
}



