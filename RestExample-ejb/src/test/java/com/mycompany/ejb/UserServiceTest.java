/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ejb;

import com.mycompany.entities.User;
import java.util.Collection;
import javax.ejb.embeddable.EJBContainer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Brian
 */
public class UserServiceTest
{
    private User user1;
    private User user2;
    
    
    public UserServiceTest()
    {
    }
    
    @Before
    public void createTestUsers()
    {
        user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Rick");
        user1.setLastName("Roll");
        user1.setAccountId("ABCD-1234-EFGH-5678");
        user1.setSsn("123-45-6789");
        
        user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Sterling");
        user2.setLastName("Archer");
        user2.setAccountId("EFGH-5678-ABCD-1234");
        user2.setSsn("987-65-4321");
    }

    /**
     * Empty result test of {@link UserService#getAllUsers()}. Should return
     * an empty Collection
     * Relies on the fact that @PostConstruct will not be called without a
     * container
     */
    @Test
    public void testGetAllUsers_noUsers() throws Exception
    {
        UserService userService = new UserService();
        Collection<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(0, users.size());
    }
    
    /**
     * Positive test of {@link UserService#getAllUsers()}.
     * Depends on the {@link UserService#addUser(com.mycompany.entities.User)}
     * method to add the users.
     */
    @Test
    public void testGetAllUsers_someUsers() throws Exception
    {
        UserService userService = new UserService();
        userService.addUser(user1);
        userService.addUser(user2);
        Collection<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    /**
     * Positive test of {@link UserService#getUserById(long)}.
     * Depends on the {@link UserService#addUser(com.mycompany.entities.User)}
     * method to add the users.
     */
    @Test
    public void testGetUserById_userExists() throws Exception
    {
        UserService userService = new UserService();
        userService.addUser(user1);
        userService.addUser(user2);
        
        assertEquals(user1, userService.getUserById(user1.getId()));
        assertEquals(user2, userService.getUserById(user2.getId()));
    }

    /**
     * Negative test of {@link UserService#getUserById(long)} method.
     * Depends on the {@link UserService#addUser(com.mycompany.entities.User)}
     * method to add the users.
     */
    @Test(expected = UserNotFoundException.class)
    public void testGetUserById_userDoesNotExist() throws Exception
    {
        UserService userService = new UserService();
        userService.addUser(user1);
        userService.addUser(user2);
        
        userService.getUserById(Integer.MIN_VALUE);
    }
    
    /**
     * Positive test of {@link UserService#addUser(com.mycompany.entities.User)}
     * Depends on {@link UserService#getAllUsers()} for validation
     */
    @Test
    public void testAddUser_success() throws Exception
    {
        UserService userService = new UserService();
        userService.addUser(user1);
        userService.addUser(user2);
        
        Collection<User> users = userService.getAllUsers();
        
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
    
    /**
     * Negative test of {@link UserService#addUser(com.mycompany.entities.User)}
     * Depends on {@link UserService#getAllUsers()} for validation
     */
    @Test(expected = UserAlreadyExistsException.class)
    public void testAddUser_duplicateAdd() throws Exception
    {
        UserService userService = new UserService();
        userService.addUser(user1);
        userService.addUser(user1);
    }
}
