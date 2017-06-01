package javadao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ResourceBundle;


@Repository
public class UserProfileDAOImpl extends BaseDAO<UserProfile> implements IUserProfileDAO {
    private static Logger log = Logger.getLogger(UserProfileDAOImpl.class);
    private ResourceBundle rb = ResourceBundle.getBundle("queryHQL");
    private final static String errorMessage = MessageDAO.getProperty("message.dataBase");
    UserProfile foundUserProfile = null;

    @Autowired
    public UserProfileDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public UserProfile getUserProfileByType(String type) throws DAOException {
        try {
            log.info("start getUserProfileByType in UserProfileDAOImpl with type:" + type);
            String hql = rb.getString("SELECT_USER_PROFILE_TYPE");
            Query query = getSession().createQuery(hql);
            query.setParameter("type", type);
            foundUserProfile = (UserProfile) query.uniqueResult();
            log.info("foundUserProfile: " + foundUserProfile);
            return foundUserProfile;
        } catch (Exception e) {
            log.warn("Exception in getUserProfileByType UserProfileDAOImpl", e);
            throw new DAOException(errorMessage);
        }
    }
}