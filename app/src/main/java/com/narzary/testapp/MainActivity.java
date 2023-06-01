package com.narzary.testapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    Button btn_login;
    Button signUp;
    EditText et_name;
    EditText et_email;
    EditText et_password;
    Button btn_signUp;
     EditText et_login_email;
     EditText et_login_password;
     Button btn_login_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_signupemail);
        et_password=findViewById(R.id.et_signuppassword);
        btn_signUp=findViewById(R.id.btn_signup);

        et_login_email=findViewById(R.id.et_login_email);
        et_login_password=findViewById(R.id.et_login_password);
        btn_login_login=findViewById(R.id.btn_login_login);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        btn_login=findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginView(View.VISIBLE);
                btn_login.setVisibility(View.INVISIBLE);
                signUp.setVisibility(View.INVISIBLE);
                handleLoginDialog();
            }
        });

        signUp= findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_login.setVisibility(View.INVISIBLE);
                signUp.setVisibility(View.INVISIBLE);
                signUpView(View.VISIBLE);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignUp();
            }
        });
    }

    private void handleSignUp() {
        HashMap<String,String> map=new HashMap<>();
        map.put("name",et_name.getText().toString());
        map.put("email",et_email.getText().toString());
        map.put("password",et_password.getText().toString());

        Call<Void> call=retrofitInterface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200){
                    Toast.makeText(MainActivity.this,"Sign Up Successfull",Toast.LENGTH_SHORT).show();
                    btn_login.setVisibility(View.VISIBLE);
                    signUp.setVisibility(View.VISIBLE);
                    signUpView(View.INVISIBLE);
                }else if(response.code()==400){
                    Toast.makeText(MainActivity.this, "User already signed up", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                loginView(View.INVISIBLE);
                signUpView(View.INVISIBLE);
                btn_login.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.VISIBLE);
            }
        });
    }


    private void handleLoginDialog() {

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();
                map.put("email", et_login_email.getText().toString());
                map.put("password", et_login_password.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {
                            LoginResult result = response.body();
                            loginView(View.INVISIBLE);
                            btn_login.setVisibility(View.VISIBLE);
                            signUp.setVisibility(View.VISIBLE);
                            Intent i=new Intent(MainActivity.this,Logged_in_activity.class);
                            i.putExtra("name",result.getName().toString());
                            startActivity(i);

                        } else if (response.code() == 404) {
                            Toast.makeText(MainActivity.this, "Wrong credentials..Try again!!!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        loginView(View.INVISIBLE);
                        signUpView(View.INVISIBLE);
                        btn_login.setVisibility(View.VISIBLE);
                        signUp.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void signUpView(int view){
        et_name.setVisibility(view);
        et_email.setVisibility(view);
        et_password.setVisibility(view);
        btn_signUp.setVisibility(view);
    }

    public void loginView(int visibility){
        btn_login_login.setVisibility(visibility);
        et_login_email.setVisibility(visibility);
        et_login_password.setVisibility(visibility);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    //    private void handleSignupDialog() {
//        View view=getLayoutInflater().inflate(R.layout.signup_dialog,null);
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setView(view).show();
//
//        Button btn_signup=view.findViewById(R.id.btn_signup);
//        EditText et_name=view.findViewById(R.id.et_name);
//        EditText et_email=view.findViewById(R.id.et_signupemail);
//        EditText et_password=view.findViewById(R.id.et_signuppassword);
//
//        btn_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HashMap<String,String> map=new HashMap<>();
//
//                map.put("name",et_name.getText().toString());
//                map.put("email",et_email.getText().toString());
//                map.put("password",et_password.getText().toString());
//
//                Call<Void> call=retrofitInterface.executeSignup(map);
//                call.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//
//                        if(response.code()==200){
//                            Toast.makeText(MainActivity.this, "Signed Up successfully", Toast.LENGTH_SHORT).show();
//                            View v=getLayoutInflater().inflate(R.layout.activity_main,null);
//                            setContentView(v);
//
//                        }else if(response.code()==400){
//                            Toast.makeText(MainActivity.this, "Already  registered", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
//    }
//}
}