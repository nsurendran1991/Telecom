package com.belong.telecom.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.belong.telecom.entity.Customer;
import com.belong.telecom.entity.PhoneNumber;

@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class CustomerRepositoryTests {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
    private PhoneNumberRepository phoneNumberRepository;
	
	@Test
    public void testFindByName() {
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
		phoneNumberRepository.save(phoneNumber1);
    	phoneNumberList.add(phoneNumber1);
    	PhoneNumber phoneNumber2 = new PhoneNumber(1, "9746791294", "India", "+91", false);
    	phoneNumberRepository.save(phoneNumber2);
    	phoneNumberList.add(phoneNumber2);
    	
    	repository.save(new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList));
    
                 
       Customer customer = repository.findByName("Navya");
         
        assertThat(customer.getName()).isEqualTo("Navya");
    }
	@Test
    public void testGetCustomerPhoneNumber() {
		List<PhoneNumber> phoneNumberList = new ArrayList<>();		
		PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
		phoneNumberRepository.save(phoneNumber1);
    	phoneNumberList.add(phoneNumber1);
    	PhoneNumber phoneNumber2 = new PhoneNumber(1, "9746791294", "India", "+91", false);
    	phoneNumberRepository.save(phoneNumber2);
    	phoneNumberList.add(phoneNumber2);
    	
    	repository.save(new Customer(1, "Navya", "1200", "118 Kavanagh Street", phoneNumberList));
    
                 
    	List<PhoneNumber> phones = repository.getCustomerPhoneNumber("Navya");
         
        assertThat(phones).isEqualTo(phoneNumberList);
    }
	
	@Test
    public void testFindByName_CustomerNotFound() {
		         
       Customer customer = repository.findByName("Navya");
         
        assertThat(customer).isEqualTo(null);
    }

}
