package net.therap.web;

import javassist.expr.NewArray;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/20/12
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class EditPhotoForm {
    private static final Logger log = LoggerFactory.getLogger(EditPhotoForm.class);

    @Autowired
    private PhotoManager photoManager;

    @Autowired
    private PhotoValidator photoValidator;

    @RequestMapping(value = "editphoto.html", method = RequestMethod.GET)
    public String showForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("User") == null) {
            return "redirect:loginform.html";
        }

        long photoId = ServletRequestUtils.getLongParameter(request, "photoId", -1);
        Photo photo = photoManager.getPhoto(photoId);
        session.setAttribute("Photo", photo);
        User user = (User) session.getAttribute("User");
        model.addAttribute("photo", photo);
        model.addAttribute("loginName", user.getLoginName());
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("photoTagList", photoManager.getAllTags());

        PhotoCmd photoCmd = new PhotoCmd(photo.getCaption(), photo.getLocation(), photo.getDescription());
        model.addAttribute("photoCmd", photoCmd);
        return "editphoto";
    }

    @RequestMapping(value = "editphoto.html", method = RequestMethod.POST)
    public String processForm(@ModelAttribute("photoCmd") PhotoCmd photoCmd, BindingResult result,
                              ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        log.info("in post upload");
        photoValidator.validate(photoCmd, result);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        Photo photo = (Photo) session.getAttribute("Photo");

        if (result.hasErrors()) {
            model.addAttribute("photo", photo);
            model.addAttribute("loginName", user.getLoginName());
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("photoForm", photoCmd);
            return "editphoto";
        }

        photo.setCaption(photoCmd.getCaption());
        photo.setLocation(photoCmd.getLocation());
        photo.setDescription(photoCmd.getDescription());

        if (!photoCmd.getTag().equals("Select")) {
            List<PhotoTag> photoTags = new ArrayList<PhotoTag>();

            List<PhotoTag> photoTagExisting = photoManager.getPhotoTags(photo);
            for (PhotoTag p : photoTagExisting) {
                photoTags.add(p);
            }
            PhotoTag photoTag = photoManager.getPhotoTagObj(photoCmd.getTag());
            photoTags.add(photoTag);

            photo.setPhotoTags(photoTags);
        }

        photoManager.updatePhoto(photo);

        log.info("after upload");
        return "redirect:photodetails.html?photoId=" + photo.getPhotoId();
    }

    @RequestMapping(value = "deletephoto.html", method = RequestMethod.GET)
    public String deletePhoto(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        long photoId = ServletRequestUtils.getLongParameter(request, "photoId", -1);
        photoManager.deletePhoto(photoId);
        return "redirect:myphotos.html";
    }
}
