package com.example.scholarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addSCL extends AppCompatActivity {

    private static int num;


    String[] IncomeArr = {"choose","All","Upto 1 lakhs","Upto 5 lakhs","Upto 9 lakhs","Above 9 lakhs"};
    String[] CastArr = {"choose","All","General","Other Backward Caste (OBC)","Scheduled Caste(SC)","Scheduled Tribe(ST)"};

    String[] category = {"choose","All"
            ,"KGP"
            ,"Andhra Pradesh"
            ,"Arunachal Pradesh"
            , "Assam"
            ,"Bihar"
            ,"Chhattisgarh"
            ,"Goa"
            ,"Gujarat"
            ,"Haryana"
            ,"Himachal Pradesh"
            ,"Jammu and Kashmir"
            ,"Jharkhand"
            ,"Karnataka"
            ,"Kerala"
            ,"Madhya Pradesh"
            ,"Maharashtra"
            ,"Manipur"
            ,"Meghalaya"
            ,"Mizoram"
            ,"Nagaland"
            ,"Odisha"
            ,"Punjab"
            ,"Rajasthan"
            ,"Sikkim"
            ,"Tamil Nadu"
            ,"Telangana"
            ,"Tripura"
            ,"Uttarakhand"
            ,"Uttar Pradesh"
            ,"West Bengal"
            ,"Andaman and Nicobar Islands"
            ,"Chandigarh"
            ,"Dadra and Nagar Haveli"
            ,"Daman and Diu"
            ,"Delhi"
            ,"Lakshadweep"
            ,"Puducherry"};


    public static void NumOfSCL(int i) {
        num=i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scl);
        TextView AddBtn = findViewById(R.id.AddToFBDB);
        TextView CancelBtn = findViewById(R.id.CancelAddLayout);
        EditText EnterNameOfSCL = findViewById(R.id.TitleOfSCLAdd);
        EditText DeadlineAdd = findViewById(R.id.DeadlineAdd);
        EditText EligibilityShort = findViewById(R.id.EligibilityShortAdd);
        EditText EligibilityDetailed = findViewById(R.id.DetailsAdd);
        EditText StepsToApply = findViewById(R.id.StepsToApplyAdd);
        Spinner state = findViewById(R.id.categoryspinner);
        Spinner cast = findViewById(R.id.castspinner);
        Spinner incomeRange = findViewById(R.id.incomespinner);


        state.setSelection(0);
        cast.setSelection(0);
        incomeRange.setSelection(0);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Scholarships").child(Integer.toString(num+1));

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state.getSelectedItemPosition()==0 || incomeRange.getSelectedItemPosition()==0 || cast.getSelectedItemPosition()==0 || EnterNameOfSCL.getText().toString().equals(""))
                {
                    Toast.makeText(addSCL.this, "Fillup all values first", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("SerialNo").setValue(num + 1);
                    databaseReference.child("NameOfScholar").setValue(EnterNameOfSCL.getText().toString());
                    databaseReference.child("Deadline").setValue(DeadlineAdd.getText().toString());
                    databaseReference.child("OneLineEligibility").setValue(EligibilityShort.getText().toString());
                    databaseReference.child("Details").setValue(EligibilityDetailed.getText().toString());
                    databaseReference.child("StepsToApply").setValue(StepsToApply.getText().toString());


                    EnterNameOfSCL.setText("");
                    DeadlineAdd.setText("");
                    EligibilityShort.setText("");
                    EligibilityDetailed.setText("");
                    StepsToApply.setText("");
                    state.setSelection(0);
                    cast.setSelection(0);
                    incomeRange.setSelection(0);
                    Toast.makeText(addSCL.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterNameOfSCL.setText("");
                DeadlineAdd.setText("");
                EligibilityShort.setText("");
                EligibilityDetailed.setText("");
                StepsToApply.setText("");
                state.setSelection(0);
                cast.setSelection(0);
                incomeRange.setSelection(0);
                Toast.makeText(addSCL.this, "Cancelled", Toast.LENGTH_SHORT).show();

            }
        });


    findViewById(R.id.imageViewHomeFromSCL).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(in);
        }
    });



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(arrayAdapter);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if(state.getSelectedItemPosition()==0){

                }else {
                    databaseReference.child("Category").setValue(category[i].toString());


                }


            }
            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });



        ArrayAdapter arrayAdapterCaste = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CastArr);
        arrayAdapterCaste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cast.setAdapter(arrayAdapterCaste);

        cast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if(cast.getSelectedItemPosition()==0){

                }else {
                    databaseReference.child("Cast").setValue(CastArr[i]);

                }
            }
            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });




        ArrayAdapter arrayAdapterIncome = new ArrayAdapter(this, android.R.layout.simple_spinner_item, IncomeArr);
        arrayAdapterIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeRange.setAdapter(arrayAdapterIncome);

        incomeRange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if(incomeRange.getSelectedItemPosition()==0){

                }else {
                    databaseReference.child("Income").setValue(IncomeArr[i]);


                }


            }
            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });




    }




}