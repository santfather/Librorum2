package javaservices;

import javadao.DAOException;
import javadao.BooksType;

public interface IBooksTypeService extends IBaseService<BooksType> {
    BooksType getBooksTypeBYBookgenre(String name) throws DAOException;
}
