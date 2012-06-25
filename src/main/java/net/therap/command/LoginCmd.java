package net.therap.command;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/7/12
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */

public class LoginCmd {
    private String userName;
    private String password;

    public LoginCmd() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

