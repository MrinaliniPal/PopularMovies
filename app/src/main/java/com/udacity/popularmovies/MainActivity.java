package com.udacity.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static boolean isSinglePane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.phone_container);
        if(view == null)
            isSinglePane=false;
        else
        {
            isSinglePane=true;
            if(savedInstanceState==null)
            {

            }
        }
    }
}