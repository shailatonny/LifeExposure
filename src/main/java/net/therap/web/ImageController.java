package net.therap.web;

import net.therap.service.PhotoManager;
import net.therap.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/13/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private PhotoManager photoManager;

    @RequestMapping(value = "image/userimage.html", method = RequestMethod.GET)
    void getUserImage(HttpServletRequest request, HttpServletResponse response) {
        log.info("in imagecontroller user image");

        try {
            byte[] imageBytes = userManager.getUserImage(ServletRequestUtils.getLongParameter(request, "userId", -1));

            response.setContentType("image/jpg");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(imageBytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
        }
    }

    @RequestMapping(value = "image/photoimage.html", method = RequestMethod.GET)
    void getPhoto(HttpServletRequest request, HttpServletResponse response) {
        log.info("in image controller photo image");

        try {
            byte[] imageBytes = photoManager.getPhotoImage(ServletRequestUtils.getLongParameter(request, "photoId", -1));

            response.setContentType("image/jpg");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(imageBytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
        }
    }
}
