package net.therap.web;

import net.therap.domain.Photo;
import net.therap.domain.PhotoComments;
import net.therap.service.PhotoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/19/12
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("photodetailspublic.html")
public class PhotoDetailsPublicController {
    private static final Logger log = LoggerFactory.getLogger(PhotoDetailsController.class);

    @Autowired
    private PhotoManager photoManager;

    @RequestMapping(method = RequestMethod.GET)
    public String showComment(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        log.info("in pht com get");

        long photoId = ServletRequestUtils.getLongParameter(request, "photoId", -1);
        Photo photo = photoManager.getPhoto(photoId);
        int views = photo.getViews();
        photo.setViews(++views);
        photoManager.savePhoto(photo);
        model.addAttribute("photo", photo);

        List<PhotoComments> commentsList = photoManager.getPhotoComments(photo);
        model.addAttribute("commentList", commentsList);
        if (!commentsList.isEmpty())
            log.info("com", commentsList.get(0));

        double rating = photoManager.getRating(photo);
        model.addAttribute("rating", rating);

        return "photodetailspublic";
    }
}
