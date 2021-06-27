package com.belong.telecom.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTests {

	@Autowired
    private CustomerService service;
	
	@MockBean
    private CustomerRepository customerRepository;

	
	
	@Test
    public void testFindByName() {
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
    	phoneNumberList.add(phoneNumber1);
    	Customer customer = new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList);
	    Mockito.when(customerRepository.findByName(customer.getName()))
	      .thenReturn(customer);
		Optional<Customer> customer1 = service.getCustomer("Navya");
		assertThat(customer1.get().getName()).isEqualTo("Navya");
	}
	
	@Test
    public void testGetCustomerPhoneNumber() {
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
    	phoneNumberList.add(phoneNumber1);
    	Customer customer = new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList);
	    Mockito.when(customerRepository.getCustomerPhoneNumber(customer.getName()))
	      .thenReturn(phoneNumberList);
	    List<PhoneNumber> phones = service.getCustomerPhoneNumber("Navya");
		assertThat(phones).isEqualTo(phoneNumberList);
	}
}
