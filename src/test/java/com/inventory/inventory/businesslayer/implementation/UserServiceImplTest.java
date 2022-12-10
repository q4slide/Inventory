package com.inventory.inventory.businesslayer.implementation;

import com.inventory.inventory.businesslayer.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static UserService service;
    @BeforeAll
    public static void setUp(){
        service = new UserServiceImpl();
    }

    @Test
    public void testAddUser(){
        service.addUser("admin","admin",true);
        assertNotNull(service.getUser());
    }

    @Test
    public void testUser(){
        assertEquals("admin",service.getUser().getUsername());
        assertEquals("admin",service.getUser().getPassword());
        assertTrue(service.getUser().isAdmin());
    }
}