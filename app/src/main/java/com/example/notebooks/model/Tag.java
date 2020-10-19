package com.example.notebooks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tag implements Parcelable{
        private String tagId;
        private String tagName;

    public Tag(){}

    public Tag(String tagId, String tagname) {
        this.tagId = tagId;
        this.tagName = tagname;
    }
    public String gettagname() {
        return tagName;
    }
    public void settagname(String tagname) {
        this.tagName = tagname;
    }

    public String getTagId() {
        return tagId;
    }
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


    protected Tag(Parcel in) {
        tagId = in.readString();
        tagName = in.readString();
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel in) {
            return new Tag(in);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tagId);
        parcel.writeString(tagName);
    }

}
