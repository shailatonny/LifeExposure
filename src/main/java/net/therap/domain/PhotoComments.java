package net.therap.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/10/12
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SS_PHOTO_COMMENTS")
public class PhotoComments {
    private long photoCommentId;
    private Photo photo;
    private User commentedBy;
    private String photoComment;
    private Date commentDate;
    private long version;

    public PhotoComments() {
    }

    public PhotoComments(String photoComment) {
        this.photoComment = photoComment;
    }

    @Id
    @SequenceGenerator(name = "SS_PHOTO_COMMENTS_SEQ",sequenceName = "SS_PHOTO_COMMENTS_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "SS_PHOTO_COMMENTS_SEQ")
    @Column(name = "PHOTO_COMMENT_ID")
    public long getPhotoCommentId() {
        return photoCommentId;
    }

    public void setPhotoCommentId(long photoCommentId) {
        this.photoCommentId = photoCommentId;
    }

    @ManyToOne
    @JoinColumn(name = "PHOTO_ID")
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(User commentedBy) {
        this.commentedBy = commentedBy;
    }

    @Column(name = "PHOTO_COMMENT", nullable = false)
    public String getPhotoComment() {
        return photoComment;
    }

    public void setPhotoComment(String photoComment) {
        this.photoComment = photoComment;
    }

    @Column(name = "COMMENT_DATE", nullable = false)
    @Type(type = "date")
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
