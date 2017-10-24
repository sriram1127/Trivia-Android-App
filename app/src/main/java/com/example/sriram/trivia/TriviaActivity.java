package com.example.sriram.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TriviaActivity extends AppCompatActivity implements View.OnClickListener, TriviaActivityInterface {

    private List<Question> questions;
    private RadioGroup radioGroup = null;
    private Question question = null;
    private Choice choice = null;
    private List<String> choices = null;
    private ProgressBar progressBar = null;

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    private ImageView questionImage = null;
    private TextView questionNumberTv = null;
    private TextView questionTv = null;
    private TextView timeTv = null;
    private RadioButton radioButton = null;
    private Boolean answers[];
    private int questionNumber = 1;
    private CountDownTimer timer = null;
    private String questionSymbol = "Q";

   /* public ProgressDialog getProgressDialog() {
        return progressDialog;
    }*/

    public ImageView getQuestionImage() {
        return questionImage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        questionImage = (ImageView) findViewById(R.id.questionImage);
        //imageView.displ
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        questionNumberTv = (TextView) findViewById(R.id.questionNumber);
        questionTv = (TextView) findViewById(R.id.questionText);
        timeTv = (TextView) findViewById(R.id.timeDuration);
        timer = new CountDownTimer(200000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeTv.setText("TIME LEFT: " + (millisUntilFinished / 1000) + " seconds");
            }

            public void onFinish() {
                timeTv.setText("TIME OVER!");
                endTest();
            }
        }.start();
        if (getIntent().getExtras() != null) {
            questions = getIntent().getExtras().getParcelableArrayList("questions");
            if (questions == null || questions.size() == 0) {

            }
            answers = new Boolean[questions.size()];
            if (questions != null && questions.size() > 0) {
                question = questions.get(0);
                choice = question.getChoice();
                choices = choice.getChoice();
                radioGroup = (RadioGroup) findViewById(R.id.options);
                for (int i = 0; i < choices.size(); i++) {
                    radioButton = new RadioButton(this);
                    radioButton.setHeight(80);
                    radioButton.setText(choices.get(i));
                    radioGroup.addView(radioButton);
                }
                questionNumberTv.setText(questionSymbol + questionNumber);
                questionTv.setText(question.getText());
                getNextImage(question.getImageUrl());
            }
        }
    }

    private void getNextImage(String imageURL) {
        new QuestionImage(this).execute(imageURL);
    }

    @Override
    public void onClick(View v) {

    }

    public void quit(View v) {
        /*Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);*/
        timer.cancel();
        finish();
    }

    public void next(View v) {
        questionImage.setVisibility(View.INVISIBLE);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        int correctAnswerIndex = question.getChoice().getAnswer();
        int selectedIndex = radioGroup.indexOfChild(radioGroup.findViewById(radioButtonID)) + 1;
        answers[questionNumber - 1] = correctAnswerIndex == selectedIndex;
        ++questionNumber;

        //if (questions != null && questions.size() > 0) {
        int nextQuestionNumber = questionNumber;
        if (nextQuestionNumber < questions.size()) {
            question = questions.get(nextQuestionNumber);
            choice = question.getChoice();
            choices = choice.getChoice();
            radioGroup.removeAllViews();
            for (int i = 0; i < choices.size(); i++) {
                radioButton = new RadioButton(this);
                //radioButton.setTextColor(getResources().getColor());
                radioButton.setText(choices.get(i));
                radioGroup.addView(radioButton);
            }
            questionNumberTv.setText(questionSymbol + questionNumber);
            questionTv.setText(question.getText());
            getNextImage(question.getImageUrl());
        } else {
            endTest();
        }
        // }
    }

    private void endTest() {
        timer.cancel();
        int correctAnswersCount = 0;
        for (Boolean correct : answers) {
            if (correct != null && correct)
                ++correctAnswersCount;
        }

        Intent intent = new Intent(getBaseContext(), StatiticsActivity.class);
        intent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questions);
        intent.putExtra("percentage", correctAnswersCount * 100 / answers.length);
        startActivity(intent);
    }
}
