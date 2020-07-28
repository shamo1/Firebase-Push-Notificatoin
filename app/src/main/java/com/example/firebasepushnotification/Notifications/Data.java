package com.example.firebasepushnotification.Notifications;

public class Data {
    private String Title;
    private String Message;

    public Data() {
    }

    public Data(String title, String message) {
        Title = title;
        Message = message;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
