package com.example.scholarsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartingActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        ProgressBar progressBar=findViewById(R.id.progressBar3);


        fba = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currUser= fba.getCurrentUser();

        if(currUser==null && account == null)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent(StartingActivity.this,LoginActivity.class);
                    startActivity(i);
                }
            }, 2000);
        }else
        {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent(StartingActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }, 1500);
        }




    }
}