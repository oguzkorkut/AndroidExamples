package com.okorkut.androidui_listview2;

public class MessageModel {

    private String content;
    private String person;
    private int imageId;

    public MessageModel(String content, String person, int imageId) {
        this.content = content;
        this.person = person;
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
