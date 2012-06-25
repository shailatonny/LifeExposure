package net.therap.command;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 5/31/12
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserCmd {
    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String timeZone;
    private String country;
    private String gender;
    private String description;
    private MultipartFile profilePicThumbnail;

    public UserCmd() {
    }

    public UserCmd(String loginName, String password, String firstName, String lastName, Date birthDate, String email, String country, String gender) {
        this.loginName = loginName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getProfilePicThumbnail() {
        return profilePicThumbnail;
    }

    public void setProfilePicThumbnail(MultipartFile profilePicThumbnail) {
        this.profilePicThumbnail = profilePicThumbnail;
    }
}
