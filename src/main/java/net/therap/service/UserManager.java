package net.therap.service;

import net.therap.command.UserCmd;
import net.therap.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/6/12
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {
    public void saveUser(User user);
    public void saveUser(UserCmd userForm);
    public User getUser(long id);
    public User getUserByLoginName(String userName);
    public byte[] getUserImage(long userId)  throws Exception;
    public void updateUser(User user, UserCmd userCmd);
}
