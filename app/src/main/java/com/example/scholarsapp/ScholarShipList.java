package com.example.scholarsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class ScholarShipList extends AppCompatActivity  {

    ArrayList<LinearPojoSCL> arrayListSCL;
    RecyclerView recyclerViewSCL;
    AdapterRecyclerSCL adapterRecyclerSCL;
    private FirebaseAuth fba;


//    Boolean filterArrow = false;
//    String[] IncomeArr = {"All","Upto 1 lakhs","Upto 5 lakhs","Upto 9 lakhs","Above 9 lakhs"};
//    String[] CastArr = {"All","General","Other Backward Caste (OBC)","Scheduled Caste(SC)","Scheduled Tribe(ST)"};
//
//    String[] StatesOfResidence = {"All"
//            ,"Andhra Pradesh"
//            ,"Arunachal Pradesh"
//            , "Assam"
//            ,"Bihar"
//            ,"Chhattisgarh"
//            ,"Goa"
//            ,"Gujarat"
//            ,"Haryana"
//            ,"Himachal Pradesh"
//            ,"Jammu and Kashmir"
//            ,"Jharkhand"
//            ,"Karnataka"
//            ,"Kerala"
//            ,"Madhya Pradesh"
//            ,"Maharashtra"
//            ,"Manipur"
//            ,"Meghalaya"
//            ,"Mizoram"
//            ,"Nagaland"
//            ,"Odisha"
//            ,"Punjab"
//            ,"Rajasthan"
//            ,"Sikkim"
//            ,"Tamil Nadu"
//            ,"Telangana"
//            ,"Tripura"
//            ,"Uttarakhand"
//            ,"Uttar Pradesh"
//            ,"West Bengal"
//            ,"Andaman and Nicobar Islands"
//            ,"Chandigarh"
//            ,"Dadra and Nagar Haveli"
//            ,"Daman and Diu"
//            ,"Delhi"
//            ,"Lakshadweep"
//            ,"Puducherry"};
//    private FirebaseAuth fba;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholar_ship_list);
        ImageView HomeBtnFromSSL = findViewById(R.id.HomeFromScholarShip);
        TextView EditSCL = findViewById(R.id.EditSCL);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView helpUs = findViewById(R.id.help_us);





        EditSCL.setVisibility('8');



        arrayListSCL = new ArrayList<LinearPojoSCL>();
        HomeBtnFromSSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent In6 = new Intent(ScholarShipList.this, MainActivity.class);
                startActivity(In6);
            }
        });

        progressBar.setVisibility('0');



        recyclerViewSCL = findViewById(R.id.RecyclerViewScholarShip);
        recyclerViewSCL.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSCL.setLayoutManager(linearLayoutManager);

        adapterRecyclerSCL = new AdapterRecyclerSCL(this, arrayListSCL);
        recyclerViewSCL.setAdapter(adapterRecyclerSCL);







        EditSCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.scholarsapp.addSCL.class);

                startActivity(intent);
                progressBar.setVisibility('8');
            }
        });



        fba = FirebaseAuth.getInstance();
       GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

       GoogleSignInClient gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
          if(account==null){}else {
              if (account.getId().equals("113779361380855422139") || account.getId().equals("105074765793031332191")) {
                  EditSCL.setVisibility('0');
              } else {
                  EditSCL.setVisibility('8');
              }
          }
        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference("Scholarships");

        int i = 0;

        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            int i = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    i++;
                    LinearPojoSCL linearPojoSCL = dataSnapshot.getValue(LinearPojoSCL.class);

                    arrayListSCL.add(linearPojoSCL);
                }
                com.example.scholarsapp.addSCL.NumOfSCL(i);

                progressBar.setVisibility('8');
                adapterRecyclerSCL.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        helpUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://forms.gle/hwJM8XmE6dUNqu36A"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

}