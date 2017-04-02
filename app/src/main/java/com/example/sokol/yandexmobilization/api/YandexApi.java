package com.example.sokol.yandexmobilization.api;

import android.net.Uri;


/**
 * Created by sokol on 21.03.2017.
 */

public interface YandexApi {

    public Uri translate(String text, String toLangCode);
    public Uri translate(String text, String fromLangCode, String toLangCode);
    public Uri detectLang(String text);
    public Uri getLangs(String langCode);
}
