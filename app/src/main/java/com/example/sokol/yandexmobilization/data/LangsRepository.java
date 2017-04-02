package com.example.sokol.yandexmobilization.data;

import com.example.sokol.yandexmobilization.entities.Lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sokol on 22.03.2017.
 */

public interface LangsRepository {

    void setLangs(Map<String, String> langs);
    HashMap<String, String> getLangs();
    ArrayList<String> getLangNames();
    String getLangName(String code);
    String getLangCode(String name);
}
