package net.therap.web;

import net.therap.command.LoginCmd;
import net.therap.domain.User;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/7/12
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */

@Component("loginValidator")
public class LoginValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(LoginValidator.class);

    @Autowired
    private UserManager userManager;

    public boolean supports(Class candidate) {
        return LoginCmd.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password");

        LoginCmd loginCmd = (LoginCmd) obj;

        log.info("name", loginCmd.getUserName());
        User user =  userManager.getUserByLoginName(loginCmd.getUserName());

        if(user == null) {
            errors.rejectValue("userName", "incorrect.username");
        }
        else if(!loginCmd.getPassword().equals(user.getPassword())) {
            errors.rejectValue("password", "incorrect.password");
        }
    }
}
