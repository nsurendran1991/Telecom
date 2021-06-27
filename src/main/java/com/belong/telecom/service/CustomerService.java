package com.belong.telecom.service;

import java.util.List;
import java.util.Optional;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;

public interface CustomerService {
	
	public Optional<Customer> getCustomer(final String customerName);
	public List<PhoneNumber> getCustomerPhoneNumber(final String customerName);
	
}
