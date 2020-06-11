package com.vaadin.PersonalFinances;

import com.vaadin.PersonalFinances.API.Repositories.UserRepository;
import com.vaadin.PersonalFinances.API.Services.UserService;
import com.vaadin.PersonalFinances.API.models.Transaction;
import com.vaadin.PersonalFinances.API.models.TransactionExpenseCategories;
import com.vaadin.PersonalFinances.API.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Test
    public void getUsersTest() throws Exception {
        when(userRepository.findAll()).thenReturn(Stream
                .of(new User(), new User(), new User()).collect(Collectors.toList()));
        assertEquals(3, userService.getUsers().size());
    }
    @Test
    public void createUserFirstName() throws Exception {
        User testUser = new User("firstName", "lastName", "walletId");
        when(userRepository.insert(testUser)).thenReturn(testUser);
        assertEquals("firstName", userService.createUser(testUser).getFirstName());

    }
    @Test
    public void createUserLastName() throws Exception {
        User testUser = new User("firstName", "lastName", "walletId");
        when(userRepository.insert(testUser)).thenReturn(testUser);
        assertEquals("lastName", userService.createUser(testUser).getLastName());

    }
    @Test
    public void findUserById() throws Exception{
        User userToFind = new User("firstName", "lastName", "walletId");
        when(userRepository.findById("userId")).thenReturn(java.util.Optional.of(userToFind));
        assertEquals(userToFind, userService.getUser("userId").get());
    }
}
