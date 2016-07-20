package com.gd.zhenghy.bean;

/**
 * Created by zhenghy on 2016/7/14.
 */
public class MenuList {
    private String title;
    private int drawableId;
    private int highlighter;

    public MenuList() {
    }

    public MenuList(String title, int drawableId, int highlighter) {
        this.title = title;
        this.drawableId = drawableId;
        this.highlighter = highlighter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getHighlighter() {
        return highlighter;
    }

    public void setHighlighter(int highlighter) {
        this.highlighter = highlighter;
    }
}
