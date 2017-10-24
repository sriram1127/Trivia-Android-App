package com.example.sriram.trivia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by SRIRAM on 21-09-2016.
 */
public class QuestionImage extends AsyncTask<String, Void, Bitmap> {

    private TriviaActivityInterface triviaActivityInterface;
    private ProgressBar progressBar = null;


    public QuestionImage(TriviaActivityInterface triviaActivityInterface) {
        this.triviaActivityInterface = triviaActivityInterface;
    }

    @Override

    protected void onPreExecute() {
        progressBar = triviaActivityInterface.getProgressBar();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView questionImage = triviaActivityInterface.getQuestionImage();
        questionImage.setImageBitmap(bitmap);
        questionImage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        Bitmap bitmap = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection hrc = (HttpURLConnection) url.openConnection();
            hrc.setRequestMethod("GET");
            bitmap = BitmapFactory.decodeStream(hrc.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
