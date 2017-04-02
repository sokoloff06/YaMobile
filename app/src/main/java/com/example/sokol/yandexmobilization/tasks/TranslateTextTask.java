package com.example.sokol.yandexmobilization.tasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sokol.yandexmobilization.entities.TranslateResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

import com.example.sokol.yandexmobilization.api.QueriesBuilder;

/**
 * Created by sokol on 21.03.2017.
 */

public class TranslateTextTask extends AsyncTask<String, Void, String> {
    private ProgressBar progressBar;
    private TextView textViewTranslated;
    private String fromLangCode;
    private String toLangCode;

    public TranslateTextTask(ProgressBar progressBar, TextView textViewTranslated, String fromLangCode, String toLangCode) {
        this.progressBar = progressBar;
        this.textViewTranslated = textViewTranslated;
        this.fromLangCode = fromLangCode;
        this.toLangCode = toLangCode;
    }

    @Override
    protected String doInBackground(String... params) {
        if (params[0].isEmpty()){
            return null;
        }
        Uri uri = null;
        if (fromLangCode.equals("auto")){
            uri = new QueriesBuilder().translate(params[0], toLangCode);
        }
        else{
            uri = new QueriesBuilder().translate(params[0], fromLangCode, toLangCode);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TranslateResponse response = null;
        try {
            URL url = new URL(uri.toString());
            response = objectMapper.readValue(url, TranslateResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            return response.getText();
        }
        else{
            return "Server connection error";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        textViewTranslated.setText(result);
        textViewTranslated.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
