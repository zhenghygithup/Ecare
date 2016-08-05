package com.gd.zhenghy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhenghy on 2016/7/28.
 */
public class GrouplistBean implements Parcelable {
    private String photoimg;
    private String GroupName;
    private String yourRole;
    private int selectId;

    public GrouplistBean(String photoimg, String groupName, String yourRole, int selectId) {
        this.photoimg = photoimg;
        GroupName = groupName;
        this.yourRole = yourRole;
        this.selectId = selectId;
    }

    public GrouplistBean() {
    }

    public String getYourRole() {
        return yourRole;
    }

    public void setYourRole(String yourRole) {
        this.yourRole = yourRole;
    }

    public int getSelectId() {
        return selectId;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    public String getPhotoimg() {

        return photoimg;
    }

    public void setPhotoimg(String photoimg) {
        this.photoimg = photoimg;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photoimg);
        dest.writeString(this.GroupName);
        dest.writeString(this.yourRole);
        dest.writeInt(this.selectId);
    }

    private GrouplistBean(Parcel in) {
        this.photoimg = in.readString();
        this.GroupName = in.readString();
        this.yourRole = in.readString();
        this.selectId = in.readInt();
    }

    public static final Parcelable.Creator<GrouplistBean> CREATOR = new Parcelable.Creator<GrouplistBean>() {
        public GrouplistBean createFromParcel(Parcel source) {
            return new GrouplistBean(source);
        }

        public GrouplistBean[] newArray(int size) {
            return new GrouplistBean[size];
        }
    };
}
