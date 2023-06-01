package com.narzary.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Logged_in_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        TextView tv_status=findViewById(R.id.tv_status);
        Intent i=getIntent();
        String name=i.getStringExtra("name");
        tv_status.setText("Welcome dear "+name);
    }
}