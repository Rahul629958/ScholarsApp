package com.example.scholarsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;

public class ProfileView extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
   FirebaseAuth firebaseAuth;
   String PicUriString;
    Handler mainHandler = new Handler();

    private int z=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        ImageView HomeBtnFromP = findViewById(R.id.HomeFormProfile);

        TextView YourName = findViewById(R.id.YourName);
        TextView YourEmail = findViewById(R.id.YourEmail);
        ImageView YourPic = findViewById(R.id.YourPic);


        firebaseAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null) {

            String name = account.getDisplayName();
            String Email = account.getEmail();
            Uri Pic = account.getPhotoUrl();
            YourName.setText(name);
            YourEmail.setText(Email);
            if (Pic == null) {z=0;
            } else {z=1;
                PicUriString = Pic.toString();
                new FetchImage(PicUriString, YourPic).start();
            }














        }




       if(account==null)
       {
           YourName.setText("Anonymous");
           YourEmail.setVisibility('8');
       }
       else{
           YourEmail.setVisibility('0');
       }




        HomeBtnFromP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In8 = new Intent(ProfileView.this,MainActivity.class);
                startActivity(In8);
            }
        });


    }



     class FetchImage extends Thread{
        String URL;
        Bitmap bitmap;
        ImageView YourImage;

        FetchImage(String URL,ImageView YourImage)
        {
            this.URL=URL;
            this.YourImage = YourImage;
        }

        @Override
        public void run()
        {
           mainHandler.post(new Runnable() {
               @Override
               public void run() {
                   //Toast.makeText(ProfileView.this, "Image loading started", Toast.LENGTH_SHORT).show();
               }
           });

            InputStream inputStream= null;
            try {
                inputStream = new java.net.URL(URL).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    YourImage.setImageBitmap(bitmap);
                }
            });

        }

    }


}