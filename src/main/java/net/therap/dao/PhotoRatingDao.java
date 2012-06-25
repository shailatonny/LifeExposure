package net.therap.dao;

import net.therap.domain.Photo;
import net.therap.domain.PhotoRating;
import net.therap.domain.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/18/12
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoRatingDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(PhotoRatingDao.class);

    public void saveRating(PhotoRating photoRating) {
        Session session = getSession();
        session.saveOrUpdate(photoRating);
        session.flush();
    }

    public double getRating(Photo photo) {
        String sql = "SELECT AVG(photoRating.rating) FROM PhotoRating AS photoRating WHERE photoRating.photo = ?";
        List result = this.getHibernateTemplate().find(sql, photo);
        return (result.get(0) != null) ? (Double) result.get(0):0;
    }

    public boolean isDoubleRating(Photo photo, User user) {
        String sql = "SELECT COUNT(*) from PhotoRating as photoRating WHERE photoRating.photo = ? AND photoRating.ratedBy = ?";
        List result = this.getHibernateTemplate().find(sql, photo, user);
        return (Long)result.get(0) > 0;
    }
}
