package javadao;


public interface IBooksTypeDAO extends IBaseDAO<BooksType> {
    public BooksType getBooksTypeByBookgenre(String bookgenre) throws DAOException;
}
