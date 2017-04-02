package com.example.sokol.yandexmobilization;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sokol.yandexmobilization.data.DBHelper;
import com.example.sokol.yandexmobilization.data.LangsRepositoryImpl;
import com.example.sokol.yandexmobilization.data.LangsRepository;
import com.example.sokol.yandexmobilization.fragments.BookmarksFragment;
import com.example.sokol.yandexmobilization.fragments.SettingsFragment;
import com.example.sokol.yandexmobilization.fragments.TranslateFragment;
import com.example.sokol.yandexmobilization.tasks.GetLangsTask;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            PreferenceFragment settings = null;
            switch (item.getItemId()) {
                case R.id.navigation_translate:
                    selectedFragment = new TranslateFragment();
                    item.setChecked(true);
                    break;
                case R.id.navigation_bookmarks:
                    selectedFragment = new BookmarksFragment();
                    item.setChecked(true);
                    break;
                case R.id.navigation_settings:
                    settings = new SettingsFragment();
                    item.setChecked(true);
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(settings == null){
                transaction.replace(R.id.content, selectedFragment);
                transaction.commit();
            }
            else{
                getFragmentManager().beginTransaction().replace(R.id.content, settings).commit();
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Fragment initialFragment = new TranslateFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, initialFragment);
        transaction.commit();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        DBHelper dbHelper = new DBHelper(this);
        LangsRepository langsRepository = new LangsRepositoryImpl(dbHelper);
        new GetLangsTask(langsRepository).execute();
    }
}
