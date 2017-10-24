package com.example.sriram.trivia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StatiticsActivity extends AppCompatActivity {

    private TextView markPercentage = null;
    // private SeekBar percentageBar = null;
    private ProgressBar progressBar = null;
    private static final String PERCENTAGE_SYMBOL = "%";
    private List<Question> questions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statitics);
        markPercentage = (TextView) findViewById(R.id.markInPercentage);
        // percentageBar = (SeekBar) findViewById(R.id.percentageBar);
        progressBar = (ProgressBar) findViewById(R.id.percentageBar);
        //percentageBar.setEnabled(false);

        if (getIntent().getExtras() != null) {
            questions = getIntent().getExtras().getParcelableArrayList("questions");
            int percentage = getIntent().getExtras().getInt("percentage");
            markPercentage.setText(Integer.toString(percentage) + PERCENTAGE_SYMBOL);
            markPercentage.setTextColor(Color.parseColor("#910675"));
            // percentageBar.setProgress(percentage);
            progressBar.setProgress(percentage);
        }
    }

    public void quit(View v) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    public void tryAgain(View v) {
        Intent intent = new Intent(getBaseContext(), TriviaActivity.class);
        intent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questions);
        startActivity(intent);
    }
}
