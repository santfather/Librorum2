package javaservices;

import javadao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.login.LoginException;
import java.util.List;

public abstract class UserService extends BaseService<User> implements IUserService {
    private static Logger log = Logger.getLogger(UserService.class);


    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IUserProfileDAO userProfileDAO;

    @Autowired
    private IBooksDAO booksDAO;

    public UserService(IBaseDAO<User> baseDAO) {
        super( baseDAO );
    }

    @Override
    public javadao.User getUserByLogin(String login) throws DAOException {
        log.info("start getUserByLogin in UserService with login:" + login);
        return userDAO.getUserByLogin(login);
    }

    @Override
    public List<javadao.User> findAll() throws DAOException {
        return userDAO.findAll();
    }

    public javadao.User registerNewUser(String login, String password, String name, String surname, String email) throws LoginException, DAOException {
        if (userDAO.getUserByLogin(login) == null) {
            User newUser;
            newUser = new User(login, password, name, surname, email, State.ACTIVE.getState());
            newUser.getUserProfiles().add(userProfileDAO.getUserProfileByType( UserProfileType.USER.getType()));
            return userDAO.create(newUser);
        } else {
            throw new LoginException();
        }
    }

    public User createNewUser(User user) throws LoginException, DAOException {
        if (userDAO.getUserByLogin(user.getLogin()) == null) {
            user.getUserProfiles().add(userProfileDAO.getUserProfileByType( UserProfileType.USER.getType()));
            return userDAO.create(user);
        } else {
            throw new LoginException();
        }
    }

    public User updateUserState(Integer id, String state) throws DAOException {
        javadao.User updatedUser = userDAO.getById(id);
        updatedUser.setState(state);
        System.out.println(updatedUser);
        return userDAO.update(updatedUser);
    }

    @Override
    public String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Override
    public void makeAnOrder(Integer id) throws DAOException {
        Books books = booksDAO.getById(id);
        javadao.User user = userDAO.getUserByLogin(getPrincipal());
        user.getBooks().add(books);
        books.getUser().add(user);
        booksDAO.saveOrUpdate(books);

    }
}

