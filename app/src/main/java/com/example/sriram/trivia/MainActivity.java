package com.example.sriram.trivia;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityInterface, View.OnClickListener {

    //private ProgressDialog progressDialog = null;
    private ProgressBar progressBar = null;
    private List<Question> questions = null;
    private Button startButton = null;
    private ImageView appImage = null;

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    private TextView readyLabel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.triviaLoading);

        startButton = (Button) findViewById(R.id.StartButton);
        appImage = (ImageView) findViewById(R.id.appImage);
        readyLabel = (TextView) findViewById(R.id.readyLabel);

        if (connectivity()) {
            new TriviaExam(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        } else {
            Toast.makeText(this, "Please connect to Internet", Toast.LENGTH_SHORT).show();
        }


    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }


    /*public ProgressDialog getProgressDialog() {
        return progressDialog;
    }*/

    public Button getStartButton() {
        return startButton;
    }

    public ImageView getAppImage() {
        return appImage;
    }

    public TextView getReadyLabel() {
        return readyLabel;
    }

    private boolean connectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onClick(View v) {

    }


    public void exitApp(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void startTriviaQuiz(View v) {
        Intent intent = new Intent(getBaseContext(),TriviaActivity.class);
        intent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questions);
        startActivity(intent);
    }
}
