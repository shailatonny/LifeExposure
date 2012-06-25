package net.therap.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: shaila
 * Date: 6/13/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SS_TAG")
public class PhotoTag {
    private long tagId;
    private String tag;
    private List<Photo> photoList;
    private long version;

    @Id
    @SequenceGenerator(name = "SS_TAG_SEQ", sequenceName = "SS_TAG_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SS_TAG_SEQ")
    @Column(name = "TAG_ID")
    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Column(name = "TAG")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @ManyToMany(mappedBy = "photoTags")
    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}