package com.belong.telecom.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.repository.PhoneNumberRepository;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class PhoneNumberRepositoryTests {
	
	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private PhoneNumberRepository repository;
    
    @Test
    public void testFindByPhoneNumber() {
        entityManager.persist(new PhoneNumber(1, "0422465122", "Australia", "+61", false));
                 
        com.belong.telecom.entity.PhoneNumber phone = repository.findByPhoneNumber("0422465122");
         
        assertThat(phone.getPhoneNumber()).isEqualTo("0422465122");
    }

    @Test
    public void testGetAllPhoneNumbers() {
        entityManager.persist(new PhoneNumber(1, "0422465122", "Australia", "+61", false));
        entityManager.persist(new PhoneNumber(2, "9746791294", "India", "+91", false));
                 
        List<PhoneNumber> phones = repository.findAll();
        assertThat(phones.size()).isEqualTo(2);
         
        
    }
    
    @Test
    public void testFindByPhoneNumber_whenNumberNotPresent() {   
        
    	com.belong.telecom.entity.PhoneNumber phone = repository.findByPhoneNumber("0422465122");
        assertThat(phone).isEqualTo(null);     
        
    }
	

}
