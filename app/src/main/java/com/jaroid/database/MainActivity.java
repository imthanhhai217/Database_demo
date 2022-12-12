package com.jaroid.database;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SqlHelper mSqlHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSqlHelper = new SqlHelper(getApplicationContext());

//        City city = new City();
//        city.setCityName("Hà Nội");
//        mSqlHelper.addCity(city);

//        City city1 = new City();
//        city1.setId(2);
//        city1.setCityName("Đà Nẵng");
////        mSqlHelper.updateCity(city1);
//
//        mSqlHelper.deleteCity(city1);

//        for (int i = 0; i < 10; i++) {
//            City city = new City();
//            city.setCityName("City " + i);
//            mSqlHelper.addCity(city);
//        }

        Log.d(TAG, "onCreate: "+mSqlHelper.getListCity().size());
        ArrayList<City> cities  = mSqlHelper.getListCity();
        for (int i = 0 ; i < cities.size(); i++){
            Log.d(TAG, "onCreate: "+cities.get(i).toString());
        }

    }
}