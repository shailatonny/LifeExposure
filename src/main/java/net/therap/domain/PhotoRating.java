package net.therap.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/17/12
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "SS_RATING", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_ID", "PHOTO_ID" }) })
public class PhotoRating {
    private long ratingId;
    private Photo photo;
    private User ratedBy;
    private double rating;
    private long version;

    public PhotoRating() {
    }

    public PhotoRating(double rating) {
        this.rating = rating;
    }

    @Id
    @SequenceGenerator(name = "SS_RATING_SEQ", sequenceName = "SS_RATING_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SS_RATING_SEQ")
    @Column(name = "RATING_ID")
    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
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
    public User getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
    }

    @Column(name = "RATING")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
