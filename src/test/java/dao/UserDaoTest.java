package dao;

import com.inventory.inventory.businesslayer.entity.User;
import com.inventory.inventory.dao.implementation.UserDaoServiceImpl;
import com.inventory.inventory.dao.services.UserDaoService;
import org.junit.jupiter.api.Test;

public class UserDaoTest {
    static final UserDaoService dao= new UserDaoServiceImpl();
    @Test
    public void addUser(){
        User user = new User("admin","admin",true);
        dao.addUser(user);
    }
}
