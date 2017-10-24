package com.example.sriram.trivia;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SRIRAM on 21-09-2016.
 */
public class Question implements Parcelable {

    private Integer questionId;

    private String text;

    private String imageUrl;

    private Choice choice;

    public Question() {
    }

    protected Question(Parcel in) {
        questionId = in.readInt();
        text = in.readString();
        imageUrl = in.readString();
        choice = in.readParcelable(Choice.class.getClassLoader());
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionId);
        dest.writeString(text);
        dest.writeString(imageUrl);
        dest.writeParcelable(choice, flags);
    }
}
