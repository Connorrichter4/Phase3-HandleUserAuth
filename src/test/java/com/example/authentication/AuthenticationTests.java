package com.example.authentication;


import com.example.authentication.models.User;
import com.example.authentication.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest

public class AuthenticationTests {
	
	@Autowired
	LoginService service;
    
    @TestConfiguration
    static class AuthenticationTestsConfiguration {
    	@Bean
    	public LoginService loginService() {
    		return new LoginService();
    	}
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        // given

        User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");

        service.AddUser(dummyUser);

        // when
        User found = service.GetUserByName(dummyUser.getName());

        // then

        assertEquals(found.getName(), dummyUser.getName());
    }
    
    @Test
    public void whenFindByEmail_thenReturnUser() {
    	// given
        User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        // adding user to the database
        service.AddUser(dummyUser);

        // when
        User found = service.GetUserByEmail(dummyUser.getEmail());

        // then
        assertEquals(found.getEmail(), dummyUser.getEmail());
    }
    
    @Test
    public void whenExistsByEmailAndPassword_thenReturnTrue() {
    	// given
        User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        service.AddUser(dummyUser);

        // when
        boolean found = service.AuthenticateByEmail(dummyUser.getEmail(), dummyUser.getPassword());
        // then
        assertTrue(found);
        
    }
    
    @Test
    public void whenAddNullUser_thenReturnFalse() {
    	
    	User dummyUser = null;
    	boolean addUser = service.AddUser(dummyUser);
    	
    	assertFalse(addUser);
    }

    @Test
    public void whenAddUser_thenReturnTrue() {
    	//given
    	
    	User dummyUser = new User();
    	dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        
        // when
        boolean newUser = service.AddUser(dummyUser);
        // then
        assertTrue(newUser);
        
    }
    
    @Test
    public void whenProvidedUserCheckPassword_thenReturnTrue() {
    	User dummyUser = new User();
    	dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        service.AddUser(dummyUser);
        
        boolean authenticated = service.AuthenticateUser(dummyUser, "password");
        
        assertTrue(authenticated);
    }

}
