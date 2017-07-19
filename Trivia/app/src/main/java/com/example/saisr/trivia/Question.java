package com.example.saisr.trivia;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdeek on 9/23/2016.
 */

public class Question implements Parcelable{
    public int id = 0;
    public String question = "";
    public String imageURL = "";
    public int answer;
    public ArrayList<String> opts = new ArrayList<>();

    public Question(int id, String question, String imageURL, ArrayList<String> opts, int answer) {
        this.id = id;
        this.question = question;
        this.imageURL = imageURL;
        this.opts = opts;
        this.answer = answer;
    }


    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        imageURL = in.readString();
        answer = in.readInt();
        opts = in.createStringArrayList();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeString(imageURL);
        parcel.writeInt(answer);
        parcel.writeStringList(opts);
    }
}
