package com.example.kdeek.hw6;

import java.io.Serializable;

/**
 * Created by kdeek on 10/19/2016.
 */
public class Location implements Serializable{

        String city;
        String country;

    public Location(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return country;
        }

        public void setState(String state) {
            this.country = state;
        }
}
