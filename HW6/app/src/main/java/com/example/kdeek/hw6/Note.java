package com.example.kdeek.hw6;

/**
 * Created by kdeek on 10/19/2016.
 */
public class Note {
    int id;
    String city,country,udate;
    double temp;
    int fav;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(int fav, double temp, String city, String country, String udate) {
        this.fav = fav;
        this.temp = temp;
        this.city = city;
        this.country = country;
        this.udate = udate;
    }

    public Note() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int isFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }


}
