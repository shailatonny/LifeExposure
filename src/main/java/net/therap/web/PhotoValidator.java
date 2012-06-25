package net.therap.web;

import net.therap.command.PhotoCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/12/12
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("photoValidator")
public class PhotoValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(PhotoValidator.class);

    public boolean supports(Class candidate) {
        return PhotoCmd.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caption", "required.caption");
    }
}
