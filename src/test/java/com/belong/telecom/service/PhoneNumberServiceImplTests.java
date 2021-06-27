package com.belong.telecom.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.exception.PhoneNumberNotFoundException;
import com.belong.telecom.repository.PhoneNumberRepository;
import com.belong.telecom.service.PhoneNumberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneNumberServiceImplTests {

	@Autowired
    private PhoneNumberService service;
	
	@MockBean
    private PhoneNumberRepository repository;
	
	@Test
    public void testFindAllPhoneNumbers() {
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
    	phoneNumberList.add(phoneNumber1);
    	PhoneNumber phoneNumber2 = new PhoneNumber(1, "9746791294", "India", "+91", false); 
    	phoneNumberList.add(phoneNumber2);
	    Mockito.when(repository.findAll())
	      .thenReturn(phoneNumberList);
	    List<PhoneNumber> phoneNumbers = service.findAllPhoneNumbers();
		assertThat(phoneNumbers).isEqualTo(phoneNumberList);
	}
	
	@Test
    public void testSave() {
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);  
		Mockito.when(repository.save(phoneNumber1))
	      .thenReturn(phoneNumber1);
		PhoneNumber phoneNumber = service.save(phoneNumber1);
		assertThat(phoneNumber1).isEqualTo(phoneNumber);
	}
	
	@Test
	public void testActivatePhoneNumber() throws PhoneNumberNotFoundException {
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);  
		PhoneNumber phoneNumber2 = new PhoneNumber(1, "0422465122", "Australia", "+61", true);  
		Mockito.when(repository.findByPhoneNumber(phoneNumber1.getPhoneNumber()))
	      .thenReturn(phoneNumber1);
		Mockito.when(repository.save(phoneNumber1))
	      .thenReturn(phoneNumber2);
		PhoneNumber phoneNumber = service.activatePhoneNumber("0422465122", true);
		assertThat(phoneNumber.getPhoneNumber()).isEqualTo(phoneNumber1.getPhoneNumber());
		assertThat(phoneNumber.isActivated()).isEqualTo(phoneNumber1.isActivated());
	}
	
	@Test
	public void testActivatePhoneNumber_PhoneNumberNotFound() throws PhoneNumberNotFoundException {
		try {
			PhoneNumber phoneNumber = service.activatePhoneNumber("0422465122", true);
		}catch(PhoneNumberNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Phone number is not found");
		}
		
	
	}
}
