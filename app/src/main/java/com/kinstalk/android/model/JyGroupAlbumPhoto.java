package com.kinstalk.android.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


import org.json.JSONObject;

public class JyGroupAlbumPhoto implements Parcelable {

    public static final Parcelable.Creator<JyGroupAlbumPhoto> CREATOR = new Creator<JyGroupAlbumPhoto>() {

        @Override
        public JyGroupAlbumPhoto[] newArray(int size) {
            return new JyGroupAlbumPhoto[size];
        }

        @Override
        public JyGroupAlbumPhoto createFromParcel(Parcel source) {
            return new JyGroupAlbumPhoto(source);
        }
    };
    private long uid;
    private long photoId;
    private long albumId;
    private long feedId;
//    private String url;
    private int status;
//    private String size;
    private long createTime;
    private int tagNums;

    private byte[] bitmap;

    public JyGroupAlbumPhoto() {
        super();

    }

    public JyGroupAlbumPhoto(Parcel source) {
        readFromParcel(source);
    }

    public JyGroupAlbumPhoto(JSONObject obj) {
        uid = obj.optLong("uid");
        photoId = obj.optLong("photo_id");
        albumId = obj.optLong("album_id");
        feedId = obj.optLong("news_id");
//        url = JsonUtils.jsonObjOptString(obj, "photo_url");
        status = obj.optInt("status");
//        size = JsonUtils.jsonObjOptString(obj, "size");
        createTime = obj.optLong("create_time");
        tagNums = obj.optInt("tag_nums");
    }

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

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }

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

    public void readFromParcel(Parcel in) {
        uid = in.readLong();

        photoId = in.readLong();
        albumId = in.readLong();
        feedId = in.readLong();
//        url = in.readString();
        status = in.readInt();
//        size = in.readString();
        createTime = in.readLong();
        tagNums = in.readInt();
        bitmap = new byte[1024];
        in.readByteArray(bitmap);
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(uid);
        out.writeLong(photoId);
        out.writeLong(albumId);
        out.writeLong(feedId);
//        out.writeString(url);
        out.writeInt(status);
//        out.writeString(size);
        out.writeLong(createTime);
        out.writeInt(tagNums);
        bitmap = new byte[1024];
        out.writeByteArray(bitmap);
    }
}
