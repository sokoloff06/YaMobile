package com.example.sokol.yandexmobilization.api;

import android.net.Uri;

/**
 * Created by sokol on 21.03.2017.
 */

public class QueriesBuilder implements YandexApi {

    public static final String GET_LANGS_URL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs";
    public static final String DETECT_LANG_URL = "https://translate.yandex.net/api/v1.5/tr.json/detect";
    public static final String TRANSLATE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    public static final String API_KEY_PARAM = "key";
    public static final String API_KEY = "trnsl.1.1.20170315T185651Z.010400ec63887d48.13cf882cbb4e9428daa4cbf12666f3e468abf45c";
    public static final String APP_LANG_CODE_PARAM = "ui";
    public static final String APP_LANG_CODE = "ru";
    public static final String TEXT_PARAM = "text";
    public static final String LANG_PARAM = "lang";

    @Override
    public Uri translate(String text, String toLangCode) {
        Uri translateUri = Uri.parse(TRANSLATE_URL);
        translateUri = translateUri.buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .appendQueryParameter(TEXT_PARAM, text)
                .appendQueryParameter(LANG_PARAM, toLangCode)
                .build();
        return translateUri;
    }

    @Override
    public Uri translate(String text, String fromLangCode, String toLangCode) {
        Uri translateUri = Uri.parse(TRANSLATE_URL);
        translateUri = translateUri.buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .appendQueryParameter(TEXT_PARAM, text)
                .appendQueryParameter(LANG_PARAM, fromLangCode + "-" + toLangCode)
                .build();
        return translateUri;
    }

    @Override
    public Uri detectLang(String text) {
        Uri detectLangUri = Uri.parse(DETECT_LANG_URL);
        detectLangUri = detectLangUri.buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .appendQueryParameter(TEXT_PARAM, text)
                .build();
        return detectLangUri;
    }

    @Override
    public Uri getLangs(String langCode) {
        Uri getLangsUri = Uri.parse(GET_LANGS_URL);
        getLangsUri = getLangsUri.buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .appendQueryParameter(APP_LANG_CODE_PARAM, APP_LANG_CODE)
                .build();
        return getLangsUri;
    }
}
