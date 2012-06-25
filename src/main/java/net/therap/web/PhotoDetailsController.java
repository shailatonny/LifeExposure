package net.therap.web;

import net.therap.command.PhotoReviewCmd;
import net.therap.domain.*;
import net.therap.service.PhotoManager;
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
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/12/12
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("photodetails.html")
public class PhotoDetailsController {
    private static final Logger log = LoggerFactory.getLogger(PhotoDetailsController.class);

    @Autowired
    private PhotoManager photoManager;

    @RequestMapping(method = RequestMethod.GET)
    public String showComment(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        log.info("in pht com get");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("User") == null) {
            return "redirect:loginform.html";
        }
        User user = (User) session.getAttribute("User");
        model.addAttribute("user", user);

        long photoId = ServletRequestUtils.getLongParameter(request, "photoId", -1);
        Photo photo = photoManager.getPhoto(photoId);
        if(user.getUserId() != photo.getUser().getUserId()) {
            int views = photo.getViews();
            photo.setViews(++views);
            photoManager.savePhoto(photo);
        }
        session.setAttribute("Photo", photo);
        model.addAttribute("photo", photo);

        List<PhotoComments> commentsList = photoManager.getPhotoComments(photo);
        model.addAttribute("commentList", commentsList);
        if (!commentsList.isEmpty())
            log.info("com", commentsList.get(0));

        double rating = photoManager.getRating(photo);
        model.addAttribute("rating", rating);

        List<PhotoTag> photoTagList = photoManager.getPhotoTags(photo);
        model.addAttribute("photoTagList", photoTagList);

        PhotoReviewCmd photoReviewCmd = new PhotoReviewCmd();
        model.addAttribute("photoReview", photoReviewCmd);
        return "photodetails";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processComment(@ModelAttribute("photoReview") PhotoReviewCmd photoReviewCmd, ModelMap model,
                                 HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");
        Photo photo = (Photo) session.getAttribute("Photo");

        String isComment = ServletRequestUtils.getStringParameter(request, "comment", null);
        String isRating = ServletRequestUtils.getStringParameter(request, "rate", null);

        log.info("aaaaaaaaaaaaaa",isComment);
        log.info("aaaaaaaaaaaaaa",isRating);
        if (isComment != null) {
            if (photoReviewCmd.getComment().length() != 0) {
                int i= photoReviewCmd.getComment().lastIndexOf(",");
                if( i != -1)
                    photoReviewCmd.setComment(photoReviewCmd.getComment().substring(0,i));
                log.info("in post of comment");
                PhotoComments photoComments = new PhotoComments(photoReviewCmd.getComment());
                log.info("comment", photoComments.getPhotoComment());

                if (photoComments == null)
                    log.info("comment null");

                photoComments.setCommentedBy(user);
                photoComments.setPhoto(photo);
                photoComments.setCommentDate(new Date());

                if (photoComments == null)
                    log.info("comment null2");

                photoManager.saveComment(photoComments);
                log.info("after save");
            }
        }

        if (isRating != null) {
            log.info("formRating!!!", photoReviewCmd.getRating());
            if (!photoManager.isDoubleRating(photo, user)) {
                PhotoRating photoRating = new PhotoRating(photoReviewCmd.getRating());
                photoRating.setRatedBy(user);
                photoRating.setPhoto(photo);
                photoManager.saveRating(photoRating);
            } else
                model.addAttribute("error", "You Have already rate this photo!!!");
        }

        model.addAttribute("user", user);
        model.addAttribute("photo", photo);

        List<PhotoComments> commentsList = photoManager.getPhotoComments(photo);
        if (!commentsList.isEmpty())
            log.info("com", commentsList.get(0));
        model.addAttribute("commentList", commentsList);
        PhotoReviewCmd photoReviewCmdNew = new PhotoReviewCmd();
        model.addAttribute("photoReview", photoReviewCmdNew);

        double rating = photoManager.getRating(photo);
        model.addAttribute("rating", rating);

        return "photodetails";
    }
}
