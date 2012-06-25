package net.therap.dao;

import net.therap.domain.Photo;
import net.therap.domain.PhotoTag;
import net.therap.domain.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.Blob;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 5/31/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(PhotoDao.class);

    public void savePhoto(Photo photo) {
        log.info("in savePhoto");
        Session session = getSession();
        session.save(photo);
        session.flush();
        /*//for checking transaction
        if(1==1)
        throw new RuntimeException();
        return 1;*/
    }

    public void updatePhoto(Photo photo) {
        log.info("in savePhoto");
        Session session = getSession();
        session.update(photo);
        session.flush();
        /*//for checking transaction
        throw new RuntimeException();*/
    }

    public Photo getPhoto(long id) {
        return (Photo) this.getHibernateTemplate().load(Photo.class, id);
    }

    public List<Photo> getPhotos(User user) {
        log.info("in getting comments");
        String query = "FROM Photo photo WHERE photo.user = :userObj ORDER BY photo.photoId";

        List<Photo> photos = this.getHibernateTemplate().findByNamedParam(query, "userObj", user);
        log.info("in dao", photos);
        return photos;
    }

    public List<Photo> getAllPhotos() {
        String query = "FROM Photo photo ORDER BY photo.photoId";

        List<Photo> photos = this.getHibernateTemplate().find(query);
        log.info("in dao", photos);
        return photos;
    }

    public Blob getPhotoImage(long photoId) {
        String query = "SELECT photo.photo FROM Photo photo WHERE photo.photoId = :photo_id";

        List<Blob> blobList = this.getHibernateTemplate().findByNamedParam(query, "photo_id", photoId);
        return (blobList.size() == 0) ? null:blobList.get(0);
    }

    public void deletePhoto(long photoId) {
        Session session = getSession();
        Photo photo = getPhoto(photoId);
        session.delete(photo);
        session.flush();
    }

    public long getPrevPhoto(long id) {
        String sql = "SELECT MAX(photo.photoId) FROM Photo AS photo WHERE photo.photoId < ?";
        List result = this.getHibernateTemplate().find(sql, id);
        log.info("rrrrrrrrrr", result);
        if(result.get(0) != null)
            return (Long) result.get(0);
        String sql2 = "SELECT MAX(photo.photoId) FROM Photo photo";
        List result2 = this.getHibernateTemplate().find(sql2);
        return (Long) result2.get(0);
    }

    public long getNextPhoto(long id) {
        String sql = "SELECT MIN(photo.photoId) FROM Photo AS photo WHERE photo.photoId > ?";
        List result = this.getHibernateTemplate().find(sql, id);
        if(result.get(0) != null)
            return (Long) result.get(0);
        String sql2 = "SELECT MIN(photo.photoId) FROM Photo photo";
        List result2 = this.getHibernateTemplate().find(sql2);
        return (Long) result2.get(0);
    }
}
