package web;

import javadao.DAOException;
import javaservices.IBooksService;
import javaservices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BooksUserController {

    @Autowired
    private IBooksService booksService;
    @Autowired
    private IUserService userService;

    public static final String USERBOOKS = "/user/booksuser";
    private final static String errorDAOException = "Something wrong with our data base";


    @RequestMapping(value = "/user/booksuser", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView booksCollection(Integer offset, Integer maxResults) throws DAOException {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("listBooks", this.booksService.getBooksForPagination(offset,maxResults));
            modelAndView.addObject("count", booksService.getCount());
            modelAndView.addObject("offset", offset);
            modelAndView.setViewName(USERBOOKS);
            return modelAndView;
        } catch (DAOException e) {
            modelAndView.addObject("message", errorDAOException);
            modelAndView.setViewName(USERBOOKS);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/order-books/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String orderBooks(@PathVariable("id") int id, Model model) throws DAOException {
        try {
            model.addAttribute("books", this.booksService.getById(id));
            userService.makeAnOrder(id);
            return "/user/order-books";
        } catch (DAOException e) {
            model.addAttribute("message", errorDAOException);
            return USERBOOKS;
        }
    }
}

