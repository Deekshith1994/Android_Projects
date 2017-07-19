package com.example.kdeek.inclass07;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kdeek on 10/3/2016.
 */
public class Item implements Parcelable{

    String tittle;
    String desc;
    String publish;
    String sURL;
    String link;
    String lURL;

    public Item(String tittle, String desc, String publish, String sURL, String link, String lURL) {
        this.tittle = tittle;
        this.desc = desc;
        this.publish = publish;
        this.sURL = sURL;
        this.link = link;
        this.lURL = lURL;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getsURL() {
        return sURL;
    }

    public void setsURL(String sURL) {
        this.sURL = sURL;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getlURL() {
        return lURL;
    }

    public void setlURL(String lURL) {
        this.lURL = lURL;
    }



    protected Item(Parcel in) {
        tittle = in.readString();
        desc = in.readString();
        publish = in.readString();
        sURL = in.readString();
        link = in.readString();
        lURL = in.readString();
    }




    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tittle);
        parcel.writeString(desc);
        parcel.writeString(publish);
        parcel.writeString(sURL);
        parcel.writeString(link);
        parcel.writeString(lURL);
    }
}
