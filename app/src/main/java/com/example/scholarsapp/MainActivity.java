package com.example.scholarsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

//import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {
    private static String NameExchanger;
    private static String CurrID;


    boolean clickNav = false;
    FirebaseAuth fba;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;


    ArrayList<LinearPojo> pojoArrayList;
    RecyclerView recyclerView;
    AdapterRecycler adapterRecycler;
    private static Bitmap bitmap;

    public static String getCurrUser()
    {return CurrID;}

     //FirebaseDatabase fbd = FirebaseDatabase.getInstance();
    public static void setNameFromUserId(TextView posterName, String userId, ImageView imageUser ) {
        String IDCurr = CurrID;
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("UsersInfo").child(userId);
        if(userId.equals("null"))
        {
           posterName.setText("Anonymous");
           imageUser.setImageResource(R.drawable.myprofileicon);
        }else
        {

            dbref.child("UserName").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(userId.equals(CurrID)){posterName.setText("You");}else{
                    posterName.setText(snapshot.getValue(String.class));}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            dbref.child("UserImageUri").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        String st = snapshot.getValue(String.class);
                        new GetImageFromUrl(imageUser).execute(st);

                    }else
                    {
                        imageUser.setImageResource(R.drawable.myprofileicon);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }








    public static class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public GetImageFromUrl(ImageView img){
            this.imageView = img;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView Profile = findViewById(R.id.ProfileIcon);
        TextView ProfileText = findViewById(R.id.ProfileText);
        ImageView Scholarship = findViewById(R.id.ScholarShipIcon);
        TextView ScholarshipText = findViewById(R.id.ScholarShipText);
        ImageView Notification = findViewById(R.id.NotificationIcon);
        TextView NotificationText = findViewById(R.id.NotificationText);
        ImageView Likes = findViewById(R.id.LikeIcon);
        TextView LikesText = findViewById(R.id.LikeText);
        ImageView SignOut = findViewById(R.id.SignOutIcon);
        TextView SignOutText = findViewById(R.id.SignOutText);
        ImageView NavBar = findViewById(R.id.NavBar);
        TextView AskQueryBtn = findViewById(R.id.AskQuery);
        ProgressBar progressBar = findViewById(R.id.progressBar2);


        Profile.setVisibility('8');
        ProfileText.setVisibility('8');
        Notification.setVisibility('8');
        NotificationText.setVisibility('8');
        Likes.setVisibility('8');
        LikesText.setVisibility('8');
        Scholarship.setVisibility('8');
        ScholarshipText.setVisibility('8');
        SignOut.setVisibility('8');
        SignOutText.setVisibility('8');


        NavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNav = !clickNav;
                if (clickNav) {
                    Profile.setVisibility('0');
                    ProfileText.setVisibility('0');
                    Notification.setVisibility('0');
                    NotificationText.setVisibility('0');
                    Likes.setVisibility('0');
                    LikesText.setVisibility('0');
                    Scholarship.setVisibility('0');
                    ScholarshipText.setVisibility('0');
                    SignOut.setVisibility('0');
                    SignOutText.setVisibility('0');

                } else {
                    Profile.setVisibility('8');
                    ProfileText.setVisibility('8');
                    Notification.setVisibility('8');
                    NotificationText.setVisibility('8');
                    Likes.setVisibility('8');
                    LikesText.setVisibility('8');
                    Scholarship.setVisibility('8');
                    ScholarshipText.setVisibility('8');
                    SignOut.setVisibility('8');
                    SignOutText.setVisibility('8');
                }
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In1 = new Intent(MainActivity.this, ProfileView.class);
                startActivity(In1);
            }
        });
        ProfileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In2 = new Intent(MainActivity.this, ProfileView.class);
                startActivity(In2);
            }
        });

        Scholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In3 = new Intent(MainActivity.this, ScholarShipList.class);
                startActivity(In3);
            }
        });
        ScholarshipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In4 = new Intent(MainActivity.this, ScholarShipList.class);
                startActivity(In4);
            }
        });
        fba = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
        {   this.CurrID= account.getId();}


        AskQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(account==null)
                {showDialog();}else{ Intent In5 = new Intent(MainActivity.this, AddQuery.class);
                    startActivity(In5);}

            }
        });




        if (account == null) {
            SignOutText.setText("Sign In");
            SignOut.setImageResource(R.drawable.signin);

        } else {
            SignOutText.setText("Sign Out");
            SignOut.setImageResource(R.drawable.signouticon);
        }


        if(account==null){

            SignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(in);
                }
            });

            SignOutText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(in);

                }
            });



        }else{SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (account != null) {
                    SignOut();
                    Toast.makeText(MainActivity.this, "Signed Out successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

            SignOutText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (account != null) {
                        SignOut();
                        Toast.makeText(MainActivity.this, "Signed Out successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

        pojoArrayList = new ArrayList<LinearPojo>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterRecycler = new AdapterRecycler(this, pojoArrayList);
        recyclerView.setAdapter(adapterRecycler);


        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference("Oquery");


        Ref.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    LinearPojo linearPojo = dataSnapshot.getValue(LinearPojo.class);

                    pojoArrayList.add(linearPojo);
                }

                adapterRecycler.notifyDataSetChanged();
                progressBar.setVisibility('8');


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        if (account == null) {
            Profile.setImageResource(R.drawable.myprofileicon);
        } else {


        FirebaseDatabase.getInstance().getReference("UsersInfo").child(account.getId()).child("UserImageUri").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String st = snapshot.getValue(String.class);
                    new GetImageFromUrl(Profile).execute(st);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





    }

    public void showDialog() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(fragmentManager,"MyDialogFragment");
    }

    private void SignOut() {


        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }





}





