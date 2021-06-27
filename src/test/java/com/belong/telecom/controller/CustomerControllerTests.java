package com.belong.telecom.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.repository.CustomerRepository;
import com.belong.telecom.service.CustomerService;

@AutoConfigureJsonTesters
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

	@Autowired
    private MockMvc mvc;

	@MockBean
    private CustomerService service;
	
	@MockBean
    private CustomerRepository customerRepository;
	
	 @Autowired
	 private JacksonTester<Customer> customerResponse;
	 
	 @Autowired
	 private JacksonTester<List<PhoneNumber>> phoneNumbers;
	
	@Test
    public void getCustomerTest() throws Exception {
        // given
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
    	phoneNumberList.add(phoneNumber1);
    	Customer customer = new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList);
	    
	    
        // when
    	Mockito.when(customerRepository.findByName(customer.getName()))
	      .thenReturn(customer);
	    Mockito.when(service.getCustomer(customer.getName()))
	      .thenReturn(Optional.of(customer));
        MockHttpServletResponse response = mvc.perform(
                get("/customer/Navya")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
        		customerResponse.write(new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList)).getJson()
        );
    }
	
	@Test
    public void getCustomerPhoneNumbersTest() throws Exception {
        // given
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
    	phoneNumberList.add(phoneNumber1);
    	Customer customer = new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList);
	    
	    
        // when
    	Mockito.when(customerRepository.findByName(customer.getName()))
	      .thenReturn(customer);
	    Mockito.when(service.getCustomer(customer.getName()))
	      .thenReturn(Optional.of(customer));
        MockHttpServletResponse response = mvc.perform(
                get("/customer/Navya/phoneNumbers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
        		phoneNumbers.write(phoneNumberList).getJson()
        );
    }
}
