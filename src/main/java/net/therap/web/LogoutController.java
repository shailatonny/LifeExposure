package net.therap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 4/26/12
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("logout.html")
public class LogoutController{
    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("User") == null) {
            return "redirect:loginform.html";
        }

        session.invalidate();
        return "redirect:loginform.html";
    }
}
