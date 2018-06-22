package com.example.kastu.firebasedemo;

public class ListItem {

    private String head;
    private String desc;
    private String imageUrl;
    private String like;
    private String hate;
    private String love;


    public ListItem() {
    }

    public ListItem(String head, String desc, String imageUrl, String like, String hate, String love) {
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.like = like;
        this.hate = hate;
        this.love = love;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getHate() {
        return hate;
    }

    public void setHate(String hate) {
        this.hate = hate;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }
}
