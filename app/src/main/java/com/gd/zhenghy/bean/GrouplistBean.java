package com.gd.zhenghy.bean;

/**
 * Created by zhenghy on 2016/7/28.
 */
public class GrouplistBean {
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
}
