package javadao;

import java.util.List;

public interface IUserDAO extends IBaseDAO<User> {
    public User getUserByLogin (String login) throws DAOException;
    List<User> findAll() throws DAOException;
    User getUserBySurname(String surname)throws DAOException;
}
