package net.therap.service;

import net.therap.domain.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/6/12
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PhotoManager {
    public void savePhoto(Photo photo);
    public void updatePhoto(Photo photo);
    public Photo getPhoto(long id);
    public List<Photo> getPhotos(User user);
    public List<Photo> getAllPhotos();
    public void saveComment(PhotoComments photoComments);
    public List<PhotoComments> getPhotoComments(Photo photo);
    public byte[] getPhotoImage(long photoId) throws Exception;
    public void saveRating(PhotoRating photoRating);
    public double getRating(Photo photo);
    public boolean isDoubleRating(Photo photo, User user);
    public List<PhotoTag> getAllTags();
    public PhotoTag getPhotoTagObj(String tag);
    public List<PhotoTag> getPhotoTags(Photo photo);
    public void deletePhoto(long photoId);
    public List<Photo> getTaggedPhotos(long photoTagId);
    public long getPrevPhoto(long photoId);
    public long getNextPhoto(long photoId);
}