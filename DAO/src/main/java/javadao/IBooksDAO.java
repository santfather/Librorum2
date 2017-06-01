package javadao;

import java.util.List;


public interface IBooksDAO extends IBaseDAO<Books> {
    List<Books> getBooks() throws DAOException;
    public List<Books> getBooks(int firstResult, int maxResult) throws DAOException;
    public Long getCounter() throws DAOException;
}
