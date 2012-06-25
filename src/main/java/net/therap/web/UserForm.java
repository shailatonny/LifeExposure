package net.therap.web;

import net.therap.command.UserCmd;
import net.therap.domain.User;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/11/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UserForm {
    private static final Logger log = LoggerFactory.getLogger(UserForm.class);

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "newuser.html", method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        UserCmd userCmd = new UserCmd();
        model.addAttribute("newUser", userCmd);
        return "newuser";
    }

    @RequestMapping(value = "newuser.html", method = RequestMethod.POST)
    public ModelAndView processForm(@ModelAttribute("newUser") UserCmd userCmd, BindingResult result, ModelMap model,
                              @RequestParam("file") MultipartFile file) {

        log.info("in post");
        userCmd.setProfilePicThumbnail(file);
        userValidator.validate(userCmd, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", userCmd);
            return new ModelAndView("newuser");
        }

        userManager.saveUser(userCmd);
        log.info("after save");
        model.addAttribute("successMsg", "successful");
        return new ModelAndView("redirect:loginform.html", model);
    }

    @RequestMapping(value = "edituser.html", method = RequestMethod.GET)
    public String showEditForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("User") == null) {
            return "redirect:loginform.html";
        }

        User user = (User) session.getAttribute("User");
        UserCmd userCmd = new UserCmd(user.getLoginName(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getBirthDate(), user.getEmail(), user.getCountry(), user.getGender());
        model.addAttribute("editUser", userCmd);
        return "edituser";
    }

    @RequestMapping(value = "edituser.html", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute("editUser") UserCmd userCmd, BindingResult result,
                                  ModelMap model, HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam("file") MultipartFile file) {
        //@todo validation checking

        log.info("after validation");
        userCmd.setProfilePicThumbnail(file);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");

        userManager.updateUser(user, userCmd);
        log.info("after edit save");
        return "redirect:myphotos.html";
    }
}
