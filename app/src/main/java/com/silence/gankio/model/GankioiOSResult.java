package com.silence.gankio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by Silence-Dell
 *
 * @time 2017/12/19 0:48
 * @des ${TODO}
 */
@Entity(tableName = "GankioiOS")
public class GankioiOSResult {
    /**
     * _id : 5a2e4011421aa90fe2f02cd1
     * createdAt : 2017-12-11T16:21:37.459Z
     * desc : 我打赌你一定没搞明白的Activity启动模式
     * publishedAt : 2017-12-15T08:59:11.361Z
     * source : web
     * type : Android
     * url : https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488588&idx=1&sn=3f7c59654835ec8d560610ba97d10fc0
     * used : true
     * who : 陈宇明
     * images : ["http://img.gank.io/27c7d4a1-9c3e-42ed-9a21-051cd9f77798"]
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
    @ColumnInfo(name = "_images")
    private List<String> images;

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "GankioAndroidResult{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", images=" + images +
                '}';
    }
}
