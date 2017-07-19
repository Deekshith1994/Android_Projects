package com.example.kdeek.inclass09;

/**
 * Created by kdeek on 10/31/2016.
 */
public class Message {

    String UserFname,UserLname,Comment,FileThumbnailId,Type,CreatedAt;
    int Id;

    public String getUserFname() {
        return UserFname;
    }

    public void setUserFname(String userFname) {
        UserFname = userFname;
    }

    public String getUserLname() {
        return UserLname;
    }

    public void setUserLname(String userLname) {
        UserLname = userLname;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getFileThumbnailId() {
        return FileThumbnailId;
    }

    public void setFileThumbnailId(String fileThumbnailId) {
        FileThumbnailId = fileThumbnailId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}
