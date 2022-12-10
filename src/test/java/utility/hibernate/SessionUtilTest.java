package utility.hibernate;

import com.inventory.inventory.utility.hibernate.SessionUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SessionUtilTest {

    @Test
    public void testSession(){
        try(Session session = SessionUtil.getSession()){
            assertNotNull(session);
        }
    }
}
