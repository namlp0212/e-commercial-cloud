package com.namlp0212.accounts.service.impl;

import com.namlp0212.accounts.dto.AccountsDto;
import com.namlp0212.accounts.dto.CustomerDetailDto;
import com.namlp0212.accounts.entity.Accounts;
import com.namlp0212.accounts.entity.Customer;
import com.namlp0212.accounts.exception.ResourceNotFoundException;
import com.namlp0212.accounts.mapper.AccountsMapper;
import com.namlp0212.accounts.mapper.CustomerMapper;
import com.namlp0212.accounts.repository.AccountsRepository;
import com.namlp0212.accounts.repository.CustomerRepository;
import com.namlp0212.accounts.service.ICustomersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public CustomerDetailDto fetchCustomerDetail(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailDto customerDetailDto = CustomerMapper.mapToCustomerDetailDto(customer, new CustomerDetailDto());
        customerDetailDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDetailDto;
    }
}
