package javaservices;

import javadao.DAOException;
import javadao.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface IUserService extends IBaseService<User> {
    User getUserByLogin(String Login) throws DAOException;
    List<User> findAll() throws DAOException;
    User registerNewUser(String login, String password, String name, String surname, String email) throws LoginException, DAOException;
    User createNewUser(User user) throws LoginException, DAOException;
    User updateUserState(Integer id, String state) throws DAOException;
    String getPrincipal() ;
    void makeAnOrder(Integer id) throws DAOException;

}

