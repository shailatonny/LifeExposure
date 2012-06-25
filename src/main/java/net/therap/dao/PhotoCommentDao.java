package net.therap.dao;

import net.therap.domain.Photo;
import net.therap.domain.PhotoComments;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/12/12
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */

public class PhotoCommentDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(PhotoCommentDao.class);

    public void saveComment(PhotoComments photoComments) {
        log.info("in saveComment");
        Session session = getSession();
        session.saveOrUpdate(photoComments);
        session.flush();
    }

    public List<PhotoComments> getPhotoComments(Photo photo) {
        log.info("in getting comments");
        String query = "FROM PhotoComments photoComments WHERE photoComments.photo = :photoObj";

        List<PhotoComments> photoComments = this.getHibernateTemplate().findByNamedParam(query, "photoObj", photo);
        log.info("in dao", photoComments);
        return photoComments;
    }
}