package com.example.sokol.yandexmobilization.fragments;

import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sokol.yandexmobilization.R;

/**
 * Created by sokol on 18.03.2017.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        ListPreference toLangPrefence = (ListPreference) findPreference("toLang");
        setListPreferenceData(toLangPrefence);
    }

    private void setListPreferenceData(ListPreference listPreference) {
        String[] langNames = {"Russian", "English"};
        String[] langCodes = {"ru", "en"};
        listPreference.setEntries(langNames);
        listPreference.setEntryValues(langCodes);
        listPreference.setDefaultValue("ru");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settigns, container, false);
        AppCompatActivity parent = (AppCompatActivity) getActivity();
        parent.setSupportActionBar((Toolbar) rootView.findViewById(R.id.toolbar_settings));
        parent.getSupportActionBar().setTitle(R.string.title_settings);
        return rootView;
    }
}
