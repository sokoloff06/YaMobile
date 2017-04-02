package com.example.sokol.yandexmobilization.tasks;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.sokol.yandexmobilization.api.QueriesBuilder;
import com.example.sokol.yandexmobilization.data.LangsRepository;
import com.example.sokol.yandexmobilization.entities.GetLangsResponse;
import com.example.sokol.yandexmobilization.entities.Lang;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sokol on 22.03.2017.
 */

public class GetLangsTask extends AsyncTask<Void, Void, Map<String, String>> {

    private LangsRepository langsRepository;

    public GetLangsTask(LangsRepository langsRepository) {
        this.langsRepository = langsRepository;
    }

    @Override
    protected Map<String, String> doInBackground(Void... params) {
        //TODO: replace "ru" by app's language settings
        Uri getLangsUri = new QueriesBuilder().getLangs("ru");
        try {
            URL getLangsUrl = new URL(String.valueOf(getLangsUri));
            ObjectMapper mapper = new ObjectMapper();
            GetLangsResponse response = mapper.readValue(getLangsUrl, GetLangsResponse.class);
            return response.getLangs();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Map<String, String> langs) {
        langsRepository.setLangs(langs);
    }
}
