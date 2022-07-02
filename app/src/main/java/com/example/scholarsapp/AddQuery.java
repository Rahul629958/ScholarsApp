package com.example.scholarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuery extends AppCompatActivity {

    FirebaseAuth fba;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;





    public void showDialog() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(fragmentManager,"MyDialogFragment");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);

        EditText Title = findViewById(R.id.TitleOfQueryadd);
        EditText Dedication = findViewById(R.id.Dedicatedadd);
        EditText Content = findViewById(R.id.ContentQueryadd);
        TextView PostBtn = findViewById(R.id.Postbtn);
        Switch switchAnonymous = findViewById(R.id.switch1);

        ImageView HomeBtnFromAQ = findViewById(R.id.HomeFromAddQuery);
        HomeBtnFromAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In7 = new Intent(AddQuery.this,MainActivity.class);
                startActivity(In7);
            }
        });


        fba = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Oquery");

        DatabaseReference count = FirebaseDatabase.getInstance().getReference("OqueryTotalNum");
          PostBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  if (account == null) {
                     showDialog();

                  }
                  else{
                  count.addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if (snapshot.exists()) {
                              int i = snapshot.getValue(int.class);
                              i = i + 1;
                              String st = Integer.toString(i);
                              dbr.child(st).child("Content").setValue(Content.getText().toString());
                              dbr.child(st).child("Title").setValue(Title.getText().toString());
                              dbr.child(st).child("DedicatedTo").setValue(Dedication.getText().toString());
                              if (switchAnonymous.isChecked()) {
                                  dbr.child(st).child("PosterSerialNo").setValue("null");
                              } else {
                                  dbr.child(st).child("PosterSerialNo").setValue(account.getId());

                              }

                              dbr.child(st).child("SerialNo").setValue(i);
                              SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy  'at' HH:mm:ss ");
                              String currentDateandTime = sdf.format(new Date());
                              dbr.child(st).child("Date").setValue(currentDateandTime);
                              count.setValue(i);
                              Content.setText("");
                              Title.setText("");
                              Dedication.setText("");
                              switchAnonymous.setChecked(false);
                              Toast.makeText(AddQuery.this, "Posted Successfully", Toast.LENGTH_SHORT).show();


                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }



              }
          });


    }

}