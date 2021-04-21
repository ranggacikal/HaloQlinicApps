package com.haloqlinic.haloqlinicapps.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencedConfig {

    public static final String PREFERENCE_HQ_APP = "prefHaloQlinicApp";
    public static final String PREFERENCE_ID_CUSTOMER = "prefIdCustomer";
    public static final String PREFERENCE_KODE = "prefKode";
    public static final String PREFERENCE_NAMA = "prefNama";
    public static final String PREFERENCE_EMAIL = "prefEmail";
    public static final String PREFERENCE_NO_HP = "prefNoHp";
    public static final String PREFERENCE_JK = "prefJk";
    public static final String PREFERENCE_TGL_LAHIR = "prefTglLahir";
    public static final String PREFERENCE_USIA = "prefUsia";
    public static final String PREFERENCE_IMG = "prefImg";
    public static final String PREFERENCE_PROVINSI = "prefProvinsi";
    public static final String PREFERENCE_KOTA = "prefKota";
    public static final String PREFERENCE_KECAMATAN = "prefKecamatan";
    public static final String PREFERENCE_ALAMAT = "prefAlamat";
    public static final String PREFERENCE_IS_LOGIN = "prefIsLogin";

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    public SharedPreferencedConfig(Context context) {

        preferences = context.getSharedPreferences(PREFERENCE_HQ_APP, Context.MODE_PRIVATE);
        preferencesEditor = preferences.edit();
    }

    public void savePrefString(String keyPref, String value){
        preferencesEditor.putString(keyPref, value);
        preferencesEditor.commit();
    }

    public void savePrefInt(String keyPref, int value){
        preferencesEditor.putInt(keyPref, value);
        preferencesEditor.commit();
    }

    public void savePrefBoolean(String keyPref, boolean value){
        preferencesEditor.putBoolean(keyPref, value);
        preferencesEditor.commit();
    }

    public String getPreferenceIdCustomer(){
        return preferences.getString(PREFERENCE_ID_CUSTOMER, "");
    }

    public String getPreferenceKode(){
        return preferences.getString(PREFERENCE_KODE, "");
    }

    public String getPreferenceNama(){
        return preferences.getString(PREFERENCE_NAMA, "");
    }

    public String getPreferenceEmail(){
        return preferences.getString(PREFERENCE_EMAIL, "");
    }

    public String getPreferenceNoHp(){
        return preferences.getString(PREFERENCE_NO_HP, "");
    }

    public String getPreferenceJk(){
        return preferences.getString(PREFERENCE_JK, "");
    }

    public String getPreferenceTglLahir(){
        return preferences.getString(PREFERENCE_TGL_LAHIR, "");
    }

    public String getPreferenceUsia(){
        return preferences.getString(PREFERENCE_USIA, "");
    }

    public String getPreferenceImg(){
        return preferences.getString(PREFERENCE_IMG, "");
    }

    public String getPreferenceProvinsi(){
        return preferences.getString(PREFERENCE_PROVINSI, "");
    }

    public String getPreferenceKota(){
        return preferences.getString(PREFERENCE_KOTA, "");
    }

    public String getPreferenceKecamatan(){
        return preferences.getString(PREFERENCE_KECAMATAN, "");
    }

    public String getPreferenceAlamat(){
        return preferences.getString(PREFERENCE_ALAMAT, "");
    }

    public Boolean getPreferenceIsLogin(){
        return preferences.getBoolean(PREFERENCE_IS_LOGIN, false);
    }
}
