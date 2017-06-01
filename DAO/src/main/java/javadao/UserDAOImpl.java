package javadao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ResourceBundle;


@Repository
public class UserDAOImpl extends BaseDAO<User> implements IUserDAO {
    private static Logger log = Logger.getLogger(UserDAOImpl.class);
    private ResourceBundle rb = ResourceBundle.getBundle("queryHQL");
    private final static String errorMessage = MessageDAO.getProperty("message.dataBase");
    User foundUser = null;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<User> findAll() throws DAOException {
        try {
            log.info("start findAll in UserDAOImpl");
            String hql = rb.getString("SELECT_USERS");
            Query query = getSession().createQuery(hql);
            return query.list();
        } catch (Exception e) {
            log.warn("Exception in findAll UserDaoImpl", e);
            throw new DAOException(errorMessage);
        }
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        try {
            log.info("start getUserByLogin in UserDAOImpl with login:" + login);
            String hql = rb.getString("SELECT_USER_BY_LOGIN");
            Query query = getSession().createQuery(hql);
            query.setString("login", login);
            foundUser = (User) query.uniqueResult();
            System.out.println(foundUser);
            return foundUser;
        } catch (Exception e) {
            log.warn("Exception in getUserByLogin UserDAOImpl", e);
            throw new DAOException(errorMessage);
        }
    }

    public User getUserBySurname(String surname) throws DAOException {
        try {
            log.info("start getUserBySurname in UserDAOImpl with surname:" + surname);
            Criteria criteria = createEntityCriteria();
            criteria.add( Restrictions.eq("surname", surname));
            foundUser = (User) criteria.uniqueResult();
            System.out.println(foundUser);
            return foundUser;
        } catch (Exception e) {
            log.warn("Exception in getUserBySurname UserDAOImpl", e);
            throw new DAOException(errorMessage);
        }
    }
}

