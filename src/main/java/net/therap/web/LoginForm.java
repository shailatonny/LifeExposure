package net.therap.web;

import net.therap.command.LoginCmd;
import net.therap.domain.Photo;
import net.therap.domain.User;
import net.therap.service.PhotoManager;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/7/12
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("loginform.html")
public class LoginForm {
    private static final Logger log = LoggerFactory.getLogger(LoginForm.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private PhotoManager photoManager;

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String successMsg = ServletRequestUtils.getStringParameter(request, "successMsg", null);
        if(successMsg != null)
            model.addAttribute("msg", "You have successfully registered. Please log in now.");
        List<Photo> photoList = photoManager.getAllPhotos();
        log.info("listSize", photoList.size());
        model.addAttribute("photoList", photoList);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("User") == null) {
            LoginCmd loginForm = new LoginCmd();
            model.addAttribute("loginForm", loginForm);
        }

        return "loginform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@ModelAttribute("loginForm") LoginCmd loginForm, BindingResult result,
                              ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        loginValidator.validate(loginForm, result);

        if (result.hasErrors()) {
            List<Photo> photoList = photoManager.getAllPhotos();
            model.addAttribute("photoList", photoList);
            model.addAttribute("loginForm", loginForm);
            return "loginform";
        }

        log.info("name", loginForm.getUserName());
        User user = userManager.getUserByLoginName(loginForm.getUserName());

        HttpSession session = request.getSession();
        session.setAttribute("User", user);

        return "redirect:myphotos.html";
    }
}


