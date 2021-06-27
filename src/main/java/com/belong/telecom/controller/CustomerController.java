package com.belong.telecom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.exception.CustomerNotFoundException;
import com.belong.telecom.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/{customerName}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("customerName")  final String customerName) throws CustomerNotFoundException{
		Optional<Customer> customer = customerService.getCustomer(customerName);
		if(customer.isPresent()) {
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		}else {
			throw new CustomerNotFoundException("customer is not found");
		}
	}
	
	@GetMapping("/customer/{customerName}/phoneNumbers")
	public ResponseEntity<List<PhoneNumber>> getCustomerPhoneNumbers(@PathVariable("customerName") final String customerName) throws CustomerNotFoundException{
		List<PhoneNumber> phoneNumbers = customerService.getCustomerPhoneNumber(customerName);
		
			return new ResponseEntity<List<PhoneNumber>>(phoneNumbers, HttpStatus.OK);
		
	}
	
}
