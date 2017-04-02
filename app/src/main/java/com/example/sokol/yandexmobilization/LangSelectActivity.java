package com.example.sokol.yandexmobilization;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.sokol.yandexmobilization.data.DBHelper;
import com.example.sokol.yandexmobilization.data.LangsRepositoryImpl;
import com.example.sokol.yandexmobilization.data.LangsRepository;

public class LangSelectActivity extends AppCompatActivity implements LangsAdapter.OnLangSelectedListener{

    private String direction;
    public static final String TO = "toLang";

    SharedPreferences sharedPreferences;
    LangsAdapter langsAdapter;

    RecyclerView recyclerViewLangs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_select);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        direction = getIntent().getStringExtra("direction");
        if(!direction.equals(TO)){
            TextView autoDetect = (TextView) findViewById(R.id.text_view_autodetect);
            autoDetect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fromLangCode", "auto");
                    editor.putString("fromLangName", "Autodetect");
                    editor.apply();
                    onBackPressed();
                }
            });
        } else{
            findViewById(R.id.text_view_autodetect).setVisibility(View.GONE);
        }

        DBHelper dbHelper = new DBHelper(this);
        LangsRepository langsRepository = new LangsRepositoryImpl(dbHelper);

        if(direction.equals(TO)){
            langsAdapter = new LangsAdapter(langsRepository, this, sharedPreferences.getString("toLangName", null));
        }
        else{
            langsAdapter = new LangsAdapter(langsRepository, this, sharedPreferences.getString("fromLangName", "auto"));
        }

        recyclerViewLangs = (RecyclerView) findViewById(R.id.rv_langs);
        recyclerViewLangs.setAdapter(langsAdapter);
        recyclerViewLangs.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_select_langs));
        getSupportActionBar().setTitle(R.string.title_lang_select);
    }

    @Override
    public void onLangSelected(int position) {
        String langCode = langsAdapter.getItemCode(position);
        String langName = langsAdapter.getItemName(position);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(direction.equals(TO)){
            editor.putString("toLangCode", langCode);
            editor.putString("toLangName", langName);
        }
        else{
            editor.putString("fromLangCode", langCode);
            editor.putString("fromLangName", langName);
        }
        editor.apply();
        onBackPressed();
    }
}
