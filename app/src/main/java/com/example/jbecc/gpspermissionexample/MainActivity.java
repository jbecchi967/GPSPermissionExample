package com.example.jbecc.gpspermissionexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LocationHelper lh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView latitude = (TextView) findViewById(R.id.tv_latitude);
        TextView longitude = (TextView) findViewById(R.id.tv_longitude);

        this.lh = new LocationHelper(latitude, longitude, this);
    }

    public void refreshLocation(View view) {
        lh.refreshLocation();
    }
}