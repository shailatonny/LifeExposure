package net.therap.command;

/**
 * Created with IntelliJ IDEA.
 * User: Saima
 * Date: 6/16/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoReviewCmd {
    private String comment;
    private double rating;

    public PhotoReviewCmd() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
