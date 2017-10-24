package com.example.sriram.trivia;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by SRIRAM on 21-09-2016.
 */
public class Choice implements Parcelable {

    private List<String> choice;

    private int answer;

    protected Choice(Parcel in) {
        choice = in.createStringArrayList();
        answer = in.readInt();
    }

    public Choice() {
    }

    public static final Creator<Choice> CREATOR = new Creator<Choice>() {
        @Override
        public Choice createFromParcel(Parcel in) {
            return new Choice(in);
        }

        @Override
        public Choice[] newArray(int size) {
            return new Choice[size];
        }
    };

    public List<String> getChoice() {
        return choice;
    }

    public void setChoice(List<String> choice) {
        this.choice = choice;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(choice);
        dest.writeInt(answer);
    }
}
