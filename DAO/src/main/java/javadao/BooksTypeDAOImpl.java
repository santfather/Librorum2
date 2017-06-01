package javadao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ResourceBundle;


@Repository
public class BooksTypeDAOImpl extends BaseDAO<BooksType> implements IBooksTypeDAO {
    private static Logger log = Logger.getLogger(BooksTypeDAOImpl.class);
    private ResourceBundle rb = ResourceBundle.getBundle("queryHQL");
    BooksType foundBooksType = null;
    private final static String errorMessage = MessageDAO.getProperty("message.dataBase");
    private String name;

    @Autowired
    public BooksTypeDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public BooksType getBooksTypeByBookgenre(String bookgenre) throws DAOException {
        try {
            log.info("start getBooksTypeByBookgenre in BooksTypeDAOImpl with name:" + name);
            String hql = rb.getString("SELECT_BOOKS_TYPE");
            Query query = getSession().createQuery(hql);
            query.setParameter("bookgenre", bookgenre);
            foundBooksType = (BooksType) query.uniqueResult();
            System.out.println(foundBooksType);
            return foundBooksType;
        } catch (Exception e) {
            log.warn("Exception in getBooksTypeByBookgenre in BooksTypeDAOImpl", e);
            throw new DAOException(errorMessage);
        }
    }
}

