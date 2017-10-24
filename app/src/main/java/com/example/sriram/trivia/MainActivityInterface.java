package com.example.sriram.trivia;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


/**
 * Created by SRIRAM on 21-09-2016.
 */
public interface MainActivityInterface {

    public ProgressBar getProgressBar();

    public void setQuestions(List<Question> questions);

    public Button getStartButton();

    public ImageView getAppImage();

    public TextView getReadyLabel();
}
