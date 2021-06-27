package com.belong.telecom.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.repository.CustomerRepository;
import com.belong.telecom.repository.PhoneNumberRepository;

@Component
public class DataLoader {

	@Autowired
	private PhoneNumberRepository phoneNumberRepository;

	@Autowired
	private CustomerRepository customerRepository;

	// method invoked during the startup
	@PostConstruct
    public void loadData() {
    	List<PhoneNumber> phoneNumberList = new ArrayList<>();
    			
    	PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
    	phoneNumberRepository.save(phoneNumber1);
    	phoneNumberList.add(phoneNumber1);
    	PhoneNumber phoneNumber2 = new PhoneNumber(1, "9746791294", "India", "+91", false);
    	phoneNumberRepository.save(phoneNumber2);
    	phoneNumberList.add(phoneNumber2);
    	customerRepository.save(new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList));
    }

	// method invoked during the shutdown
	@PreDestroy
	public void removeData() {
		customerRepository.deleteAll();
		phoneNumberRepository.deleteAll();
	}
}