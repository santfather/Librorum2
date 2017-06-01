package web;

import javadao.DAOException;
import javadao.User;
import javaservices.IBooksService;
import javaservices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;

@Controller
@RequestMapping("/")
public class RootController {
    public static final String LOGIN = "welcome/login";
    public static final String SUCCESS = "loginsuccess/loginSuccess";
    public static final String REGISTER = "welcome/register";
    private final static String errorDAOException = "Something wrong with our data base";
    private final static String errorLoginException = "Such login is already registered in the system";


    @Autowired
    private IBooksService booksService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String incomePage() {
        return LOGIN;
    }

    @RequestMapping(value = {"welcome/login"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String loginPage(ModelMap model) {
        return SUCCESS;
    }

    @RequestMapping(value = "access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        getCurrentPageName(model);
        model.addAttribute("sms", "Incorrect login or password");
        return LOGIN;
    }

    @RequestMapping(value = "loginsuccess/loginSuccess", method = RequestMethod.GET)
    public String successPage(ModelMap model) {
        model.addAttribute("user", userService.getPrincipal());
        return SUCCESS;
    }

    @RequestMapping(value = "welcome/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName(REGISTER);
        return modelAndView;
    }

    @RequestMapping(value = "newuser/add", method = RequestMethod.POST)


    public String addUser(@ModelAttribute("user") User user, ModelMap model) throws DAOException, LoginException {
        try {
            if (user != null) {
                userService.createNewUser(user);
            }
            model.addAttribute("message", "You have been successfully registered! Please login.");
            return LOGIN;
        } catch (LoginException e) {
            model.put("message", errorLoginException);
            return REGISTER;
        } catch (DAOException e) {
            model.put("message", errorDAOException);
            return REGISTER;
        }
    }

    private void getCurrentPageName(ModelMap model) {
        model.addAttribute("currentPageName", "/");
    }
}

