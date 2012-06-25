package net.therap.web;

import net.therap.command.PhotoCmd;
import net.therap.domain.Photo;
import net.therap.domain.PhotoTag;
import net.therap.domain.User;
import net.therap.service.PhotoManager;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Blob;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/11/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private PhotoManager photoManager;

    @Autowired
    private PhotoValidator photoValidator;

    @RequestMapping(value = "upload.html", method = RequestMethod.GET)
    public String showForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        PhotoCmd photoCmd = new PhotoCmd();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("User") == null) {
            return "redirect:loginform.html";
        }

        User user = (User) session.getAttribute("User");
        model.addAttribute("loginName", user.getLoginName());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("photoCmd", photoCmd);
        model.addAttribute("photoTagList", photoManager.getAllTags());
        return "upload";
    }

    @RequestMapping(value = "upload.html", method = RequestMethod.POST)
    public String processForm(@ModelAttribute("photoCmd") PhotoCmd photoCmd, BindingResult result,
                              ModelMap model, HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("file") MultipartFile file) {

        log.info("in post uploaddddd");
        photoCmd.setPhoto(file);
        photoValidator.validate(photoCmd, result);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");

        if (result.hasErrors()) {
            model.addAttribute("loginName", user.getLoginName());
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("photoForm", photoCmd);
            return "upload";
        }

        MultipartFile multipartFile = photoCmd.getPhoto();
        Blob blob = null;

        if (!multipartFile.isEmpty()) {
            try {
                blob = Hibernate.createBlob(multipartFile.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Photo photo;

        if (!photoCmd.getTag().equals("Select")) {
            PhotoTag photoTag = photoManager.getPhotoTagObj(photoCmd.getTag());
            List<PhotoTag> photoTags = new ArrayList<PhotoTag>();
            photoTags.add(photoTag);
            photo = new Photo(photoCmd.getCaption(), photoCmd.getLocation(), photoCmd.getDescription(), photoTags);
        } else {
            photo = new Photo(photoCmd.getCaption(), photoCmd.getLocation(), photoCmd.getDescription());
        }

        photo.setPhoto(blob);
        photo.setUser(user);
        photo.setUploadDate(new Date());

        session.setAttribute("Photo", photo);
        photoManager.savePhoto(photo);

        log.info("after upload");
        return "redirect:myphotos.html";
    }
}
