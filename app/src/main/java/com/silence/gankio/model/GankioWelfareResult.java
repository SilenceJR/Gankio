package com.silence.gankio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/19 0:48
 * @des ${TODO}
 */

@Entity(tableName = "GankioWelfar")
public class GankioWelfareResult {

    /**
     * _id : 5a388e4c421aa90fe2f02ced
     * createdAt : 2017-12-19T11:58:04.567Z
     * desc : 12-19
     * publishedAt : 2017-12-19T12:00:28.893Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171219115747_tH0TN5_Screenshot.jpeg
     * used : true
     * who : daimajia
     */

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String _id;
    @ColumnInfo(name = "_createdAt")
    private String createdAt;
    @ColumnInfo(name = "_desc")
    private String desc;
    @ColumnInfo(name = "_publishedAt")
    private String publishedAt;
    @ColumnInfo(name = "_source")
    private String source;
    @ColumnInfo(name = "_type")
    private String type;
    @ColumnInfo(name = "_url")
    private String url;
    @ColumnInfo(name = "_used")
    private boolean used;
    @ColumnInfo(name = "_who")
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "GankioWelfareResult{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }
}
