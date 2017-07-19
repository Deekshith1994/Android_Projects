package com.example.kdeek.inclass11;

/**
 * Created by kdeek on 10/24/2016.
 */
public class DataItem {

    String name;
    String address;
    String category;
    String imageurl;
    String largeimageurl;
    String Checkinstot;
    String Userstot;
    String hereNow;

    public DataItem(String name, String address, String category, String imageurl, String largeimageurl, String checkins, String users, String hereNow) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.imageurl = imageurl;
        this.largeimageurl = largeimageurl;
        Checkinstot = checkins;
        Userstot = users;
        this.hereNow = hereNow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLargeimageurl() {
        return largeimageurl;
    }

    public void setLargeimageurl(String largeimageurl) {
        this.largeimageurl = largeimageurl;
    }

    public String getCheckins() {
        return Checkinstot;
    }

    public void setCheckins(String checkins) {
        Checkinstot = checkins;
    }

    public String getUsers() {
        return Userstot;
    }

    public void setUsers(String users) {
        Userstot = users;
    }

    public String getHereNow() {
        return hereNow;
    }

    public void setHereNow(String hereNow) {
        this.hereNow = hereNow;
    }
}
