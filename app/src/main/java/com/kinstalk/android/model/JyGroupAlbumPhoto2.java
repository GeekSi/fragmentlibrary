package com.kinstalk.android.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class JyGroupAlbumPhoto2 implements Serializable {

    private long uid;
    private long photoId;
    private long albumId;
    private long feedId;
    private int status;
    private long createTime;
    private int tagNums;
    private byte[] bitmap;


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getFeedId() {
        return feedId;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getTagNums() {
        return tagNums;
    }

    public void setTagNums(int tagNums) {
        this.tagNums = tagNums;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }
}
