package net.therap.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 6/10/12
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SS_PHOTO_DETAILS")
public class PhotoDetails {
    private long photoDetailsId;
    private Date captureDate;
    private String dimension;
    private String cameraModel;
    private int isoSpeed;
    private boolean flush;
    private float exposure;
    private long version;

    public PhotoDetails() {
    }

    @Id
    @SequenceGenerator(name = "SS_PHOTO_DETAILS_SEQ", sequenceName = "SS_PHOTO_DETAILS_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SS_PHOTO_DETAILS_SEQ")
    @Column(name = "PHOTO_DETAILS_ID")
    public long getPhotoDetailsId() {
        return photoDetailsId;
    }

    public void setPhotoDetailsId(long photoDetailsId) {
        this.photoDetailsId = photoDetailsId;
    }

    @Column(name = "CAPTURE_DATE")
    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    @Column(name = "DIMENSION")
    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Column(name = "CAMERA_MODEL")
    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    @Column(name = "ISO_SPEED")
    public int getIsoSpeed() {
        return isoSpeed;
    }

    public void setIsoSpeed(int isoSpeed) {
        this.isoSpeed = isoSpeed;
    }

    @Column(name = "FLUSH")
    public boolean isFlush() {
        return flush;
    }

    public void setFlush(boolean flush) {
        this.flush = flush;
    }

    @Column(name = "EXPOSURE")
    public float getExposure() {
        return exposure;
    }

    public void setExposure(float exposure) {
        this.exposure = exposure;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
