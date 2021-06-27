package com.belong.telecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Customer findByName(final String name);
	public List<PhoneNumber> getCustomerPhoneNumber(final String name);
}
