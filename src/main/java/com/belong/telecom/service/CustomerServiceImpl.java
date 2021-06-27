package com.belong.telecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Optional<Customer> getCustomer(final String customerName) {
		return Optional.ofNullable(customerRepository.findByName(customerName));
	}

	@Override
	public List<PhoneNumber> getCustomerPhoneNumber(final String customerName) {
		return customerRepository.getCustomerPhoneNumber(customerName);
	}

}
