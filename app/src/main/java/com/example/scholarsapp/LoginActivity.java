package com.example.scholarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


 GoogleSignInOptions gso;
 GoogleSignInClient gsc;
 FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        TextView SignUpLogIn = findViewById(R.id.textView3);
        TextView sendOTP = findViewById(R.id.SendOTP);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });


firebaseAuth= FirebaseAuth.getInstance();
   SignUpLogIn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           anonymousAuth();
       }
   });



    }

    private void anonymousAuth() {

     firebaseAuth.signInAnonymously().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
         @Override
         public void onSuccess(AuthResult authResult) {
             Toast.makeText(LoginActivity.this, "Signed In Anonymously", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), com.example.scholarsapp.MainActivity.class);
             startActivity(intent);
         }
     });
     firebaseAuth.signInAnonymously().addOnFailureListener((new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(LoginActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
         }
     }));

    }

    private void SignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void HomeActivity() {

        finish();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        DatabaseReference Ref = database.getReference("UsersInfo").child(account.getId().toString());
        Ref.child("UserName").setValue(account.getDisplayName());
        Ref.child("UserEmail").setValue(account.getEmail());
        if (account.getPhotoUrl()!=null) {
            Ref.child("UserImageUri").setValue(account.getPhotoUrl().toString());
        }


        Toast.makeText(this, "Signed In successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), com.example.scholarsapp.MainActivity.class);
        startActivity(intent);
    }
}