package javaservices;

import javadao.IBooksTypeDAO;
import javadao.DAOException;
import javadao.BooksType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional
public abstract class BooksTypeService extends BaseService<BooksType> implements IBooksTypeService {

    @Autowired
    private IBooksTypeDAO booksTypeDAO;

     public BooksType getBooksTypeByBookgenre(String name) throws DAOException {
        return booksTypeDAO.getBooksTypeByBookgenre(name);
    }
   }

