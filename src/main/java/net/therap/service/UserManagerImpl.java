package net.therap.service;

import net.therap.command.UserCmd;
import net.therap.dao.UserDao;
import net.therap.domain.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/4/12
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("UserManager")
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public void saveUser(UserCmd userForm) {
        MultipartFile multipartFile = userForm.getProfilePicThumbnail();
        Blob blob = null;
        if (!multipartFile.isEmpty()) {
            try {
                blob = Hibernate.createBlob(multipartFile.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        User user = new User(userForm.getLoginName(), userForm.getPassword(), userForm.getFirstName(), userForm.getLastName(),
                userForm.getBirthDate(), userForm.getEmail(), userForm.getCountry(), userForm.getGender());
        user.setJoinDate(new Date());
        user.setProfilePicThumbnail(blob);
        userDao.saveUser(user);
    }

    public void updateUser(User user, UserCmd userCmd) {
        if (userCmd.getPassword().trim() != null)
            user.setPassword(userCmd.getPassword());
        user.setFirstName(userCmd.getFirstName());
        user.setLastName(userCmd.getLastName());
        if (userCmd.getBirthDate() != null)
            user.setBirthDate(userCmd.getBirthDate());
        user.setEmail(userCmd.getEmail());
        user.setCountry(userCmd.getCountry());
        user.setGender(userCmd.getGender());

        MultipartFile multipartFile = userCmd.getProfilePicThumbnail();
        Blob blob = null;
        if (!multipartFile.isEmpty()) {
            try {
                blob = Hibernate.createBlob(multipartFile.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        user.setProfilePicThumbnail(blob);

        userDao.updateUser(user);
    }

    public User getUser(long id) {
        return (User) userDao.getUser(id);
    }

    public User getUserByLoginName(String userName) {
        return userDao.getUserByLoginName(userName);
    }

    public byte[] getUserImage(long userId) throws Exception {
        Blob imageData = userDao.getUserImage(userId);

        if (imageData == null) {
            return null;
        }

        byte[] bytes = new byte[(int) imageData.length()];
        imageData.getBinaryStream().read(bytes);
        imageData.getBinaryStream().close();
        return bytes;
    }
}
