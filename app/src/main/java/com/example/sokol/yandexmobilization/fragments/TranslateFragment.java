package com.example.sokol.yandexmobilization.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sokol.yandexmobilization.LangSelectActivity;
import com.example.sokol.yandexmobilization.R;

import com.example.sokol.yandexmobilization.tasks.TranslateTextTask;

/**
 * Created by sokol on 18.03.2017.
 */

public class TranslateFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    EditText editTextTranslate;
    TextView textViewTranslated;
    Button buttonFromLang;
    Button buttonToLang;
    ImageButton buttonReverseLangs;
    SharedPreferences sharedPreferences;
    ProgressBar pbTranslateTask;

    public static final int EDIT_DELAY = 500;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);

        pbTranslateTask = (ProgressBar) rootView.findViewById(R.id.pb_translate_task);

        buttonFromLang = (Button) rootView.findViewById(R.id.button_from_lang);
        buttonFromLang.setText(sharedPreferences.getString("fromLangName", "Autodetect"));
        buttonFromLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFromLang();
            }
        });

        buttonToLang = (Button) rootView.findViewById(R.id.button_to_lang);
        buttonToLang.setText(sharedPreferences.getString("toLangName", "Select language"));
        buttonToLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectToLang();
            }
        });

        buttonReverseLangs = (ImageButton) rootView.findViewById(R.id.button_reverse_langs);
        buttonReverseLangs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseLangs();
            }
        });

        Toolbar toolbarTranslate = (Toolbar) rootView.findViewById(R.id.toolbar_translate);
        AppCompatActivity parent = (AppCompatActivity)getActivity();
        parent.setSupportActionBar(toolbarTranslate);
        parent.getSupportActionBar().setDisplayShowTitleEnabled(false);

        textViewTranslated = (TextView) rootView.findViewById(R.id.text_view_translated);

        editTextTranslate = (EditText) rootView.findViewById(R.id.edit_text_translate);
        editTextTranslate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void afterTextChanged(final Editable s) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgressBar();
                        new TranslateTextTask(pbTranslateTask, textViewTranslated, sharedPreferences.getString("fromLangCode", "auto"), sharedPreferences.getString("toLangCode", "ru")).execute(String.valueOf(s));
                    }
                }, EDIT_DELAY);
            }
        });
        return rootView;
    }

    private void reverseLangs() {
        String toLangCode = sharedPreferences.getString("fromLangCode", "ru");
        if(!toLangCode.equals("auto")){
            String toLangName = sharedPreferences.getString("fromLangName", "Русский");
            //TODO: consider default values as auto and sysLanguage
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String fromLangCode = sharedPreferences.getString("toLangCode", "auto");
            String fromLangName = sharedPreferences.getString("toLangName", "Autodetect");
            editor
                    .putString("fromLangName", fromLangName)
                    .putString("fromLangCode", fromLangCode)
                    .putString("toLangCode", toLangCode)
                    .putString("toLangName", toLangName)
                    .apply();
            buttonFromLang.setText(fromLangName);
            buttonToLang.setText(sharedPreferences.getString("toLangName", "Русский"));
        }
        else{
            Toast.makeText(getContext(), "To swap languages you have to choose both", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectToLang() {
        Intent select = new Intent(getContext(), LangSelectActivity.class);
        select.putExtra("direction", "toLang");
        startActivity(select);
    }

    private void selectFromLang() {
        Intent select = new Intent(getContext(), LangSelectActivity.class);
        select.putExtra("direction", "fromLang");
        startActivity(select);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("toLangName")){
            String toLang = sharedPreferences.getString(key, "Select language");
            buttonToLang.setText(toLang);
        }
        if(key.equals("fromLangName")){
            String fromLang = sharedPreferences.getString(key, "Select language");
            buttonFromLang.setText(fromLang);
        }
        showProgressBar();
        new TranslateTextTask(pbTranslateTask, textViewTranslated,
                sharedPreferences.getString("fromLangCode", "auto"),
                sharedPreferences.getString("toLangCode", "ru"))
                .execute(String.valueOf(editTextTranslate.getText()));
    }

    void showProgressBar(){
        textViewTranslated.setVisibility(View.GONE);
        pbTranslateTask.setVisibility(View.VISIBLE);
    }
}


