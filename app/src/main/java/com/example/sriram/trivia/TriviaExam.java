package com.example.sriram.trivia;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by SRIRAM on 21-09-2016.
 */
public class TriviaExam extends AsyncTask<String, Void, List<Question>> {

    private ProgressBar progressBar = null;

    public TriviaExam(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
    }

    private MainActivityInterface mainActivityInterface = null;


    @Override
    protected void onPreExecute() {
        progressBar = mainActivityInterface.getProgressBar();
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPostExecute(List<Question> questions) {
        mainActivityInterface.setQuestions(questions);
        mainActivityInterface.getStartButton().setEnabled(true);
        mainActivityInterface.getAppImage().setVisibility(View.VISIBLE);
        mainActivityInterface.getReadyLabel().setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    protected List<Question> doInBackground(String... params) {
        StringBuilder sb = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection hrc = (HttpURLConnection) url.openConnection();
            hrc.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(hrc.getInputStream()));
            String line = "";
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return QuestionJsonParser.parseQuestions(sb.toString());
    }

}


