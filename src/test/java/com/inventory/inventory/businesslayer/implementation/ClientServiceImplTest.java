package com.inventory.inventory.businesslayer.implementation;

import com.inventory.inventory.businesslayer.services.ClientService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {
    private static ClientService service,service1;

    @BeforeAll
    public static void setUp(){
        service = new ClientServiceImpl();
        service1 = new ClientServiceImpl();
    }
    @Test
    void addClient() {
        service.addClient("","","test@gmail.com","+456781213407");
        assertNotNull(service.getClient());
    }
    @Test
    void addClientThrowable(){
        Exception exception = assertThrows(RuntimeException.class,
                ()->{ service1.addClient("dsa","dsa","dsa","dsa");});
        assertTrue(exception.getMessage().contains("Wrong information"));
    }
}