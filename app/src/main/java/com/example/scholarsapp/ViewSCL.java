package com.example.scholarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewSCL extends AppCompatActivity {
       Boolean StepsBool = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scl);
        TextView NameOfSCL = findViewById(R.id.Namescl);
        TextView Category = findViewById(R.id.Categoryscl);
        TextView Cast = findViewById(R.id.Castscl);
        TextView IncomeRange = findViewById(R.id.IncomeRangescl);
        TextView Details = findViewById(R.id.DetailsTextscl);
        TextView detailsBtn = findViewById(R.id.Detailsscl);
        TextView StepsApply = findViewById(R.id.StepsTextscl);
        TextView deadlinescl = findViewById(R.id.Deadlinescl);
        ImageView arrowSCL = findViewById(R.id.Arrowscl);
        arrowSCL.setImageResource(R.drawable.rightarrow);



        Intent intent = getIntent();

        String serialNo = intent.getStringExtra("SerialNo");
        Toast.makeText(this, " Here Serial No is "+serialNo, Toast.LENGTH_SHORT).show();
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Scholarships").child(serialNo);
        dbr.child("NameOfScholar").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NameOfSCL.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbr.child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Category.setText("For " + snapshot.getValue(String.class) + " students");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbr.child("Income").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                IncomeRange.setText("For "+ snapshot.getValue(String.class) +" income categories");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbr.child("Cast").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cast.setText("For "+ snapshot.getValue(String.class) + " students");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        dbr.child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Details.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbr.child("StepsToApply").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StepsApply.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbr.child("Deadline").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deadlinescl.setText("Apply before " + snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
            StepsApply.setVisibility('8');
        findViewById(R.id.Stepsscl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepsBool=!StepsBool;
                if(StepsBool)
                {
                    deadlinescl.setVisibility('8');
                    detailsBtn.setVisibility('8');
                    Details.setVisibility('8');
                    Category.setVisibility('8');
                    Cast.setVisibility('8');
                    IncomeRange.setVisibility('8');
                    StepsApply.setVisibility('0');
                    arrowSCL.setImageResource(R.drawable.downarrow);


                }
                else
                {
                    deadlinescl.setVisibility('0');
                    detailsBtn.setVisibility('0');
                    Details.setVisibility('0');
                    Category.setVisibility('0');
                    Cast.setVisibility('0');
                    IncomeRange.setVisibility('0');
                    StepsApply.setVisibility('8');
                    arrowSCL.setImageResource(R.drawable.rightarrow);

                }
            }
        });


    }
}