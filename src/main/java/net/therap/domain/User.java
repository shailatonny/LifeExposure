package net.therap.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 5/31/12
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SS_USER")
public class User {
    private long userId;
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
    private Date joinDate;
    private Blob profilePicThumbnail;
    private Set<Photo> photoSet;
    private Set<PhotoComments> photoCommentsSet;
    private Set<PhotoRating> photoRatingSet;
    private long version;

    public User(String loginName, String password, String firstName, String lastName, Date birthDate, String email, String country, String gender) {
        this.loginName = loginName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public User(String password, String firstName, String lastName, Date birthDate, String email, String country, String gender) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public User() {
    }

    @Id
    @SequenceGenerator(name = "SS_USER_SEQ", sequenceName = "SS_USER_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SS_USER_SEQ")
	@Column(name = "USER_ID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "LOGIN_NAME", nullable = false, unique = true)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "DATE_OF_BIRTH")
    @Type(type = "date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "TIME_ZONE")
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Column(name = "COUNTRY", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "GENDER", nullable = false)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "JOIN_DATE", nullable = false)
    @Type(type = "date")
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Lob
    @Column(name = "PROFILE_PIC_THUMB", columnDefinition = "blob")
    public Blob getProfilePicThumbnail() {
        return profilePicThumbnail;
    }

    public void setProfilePicThumbnail(Blob profilePicThumbnail) {
        this.profilePicThumbnail = profilePicThumbnail;
    }

    @OneToMany(mappedBy = "user",
            targetEntity = Photo.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Photo> getPhotoSet() {
        return photoSet;
    }

    public void setPhotoSet(Set<Photo> photoSet) {
        this.photoSet = photoSet;
    }

    @OneToMany(mappedBy = "commentedBy",
            targetEntity = PhotoComments.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<PhotoComments> getPhotoCommentsSet() {
        return photoCommentsSet;
    }

    public void setPhotoCommentsSet(Set<PhotoComments> photoCommentsSet) {
        this.photoCommentsSet = photoCommentsSet;
    }

    @OneToMany(mappedBy = "ratedBy",
            targetEntity = PhotoRating.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<PhotoRating> getPhotoRatingSet() {
        return photoRatingSet;
    }

    public void setPhotoRatingSet(Set<PhotoRating> photoRatingSet) {
        this.photoRatingSet = photoRatingSet;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
