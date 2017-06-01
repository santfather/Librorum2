package javadao;


import java.io.Serializable;

public interface IBaseDAO <T> {
        T create (T t) throws DAOException;
        T loadById(Serializable id) throws DAOException;
        T saveOrUpdate(T t) throws DAOException;
        T getById(Serializable id) throws DAOException;
        T update(T t) throws DAOException;
    public void delete(T t) throws DAOException;
}
