package net.therap.command;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/13/12
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoCmd {
    private MultipartFile photo;
    private String privacy = "public";
    private String caption;
    private String location;
    private String description;
    private String tag;

    public PhotoCmd() {
    }

    public PhotoCmd(String caption, String location, String description) {
        this.caption = caption;
        this.location = location;
        this.description = description;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
