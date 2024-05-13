package com.namlp0212.accounts.service;

import com.namlp0212.accounts.dto.CustomerDetailDto;

public interface ICustomersService {
    CustomerDetailDto fetchCustomerDetail(String mobileNumber);
}
