package javadao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserDAOImplTest {
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IUserProfileDAO userProfileDAO;

    @Test
    public void getUserByLogin() throws Exception {

    }

    @Test
    public void getUserBySurname() throws Exception {

    }

}