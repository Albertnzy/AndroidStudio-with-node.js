package com.narzary.testapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends Fragment {

    public Signup() {
        // Required empty public constructor
    }

    EditText et_name;
    EditText et_email;
    EditText et_password;
    Button btn_signUp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_signup, container, false);
        et_name=v.findViewById(R.id.et_name);
        et_email=v.findViewById(R.id.et_signupemail);
        et_password=v.findViewById(R.id.et_signuppassword);
        btn_signUp=v.findViewById(R.id.btn_signup);
        return v;
    }
}