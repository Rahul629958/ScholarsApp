package com.example.scholarsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerSCL extends RecyclerView.Adapter<AdapterRecyclerSCL.MyHolder>{

    Context context;
    ArrayList<LinearPojoSCL> arrayListSCL;
    ArrayList<LinearPojoSCL> arrayListSCLtemp;


    public AdapterRecyclerSCL(Context context,ArrayList<LinearPojoSCL> arrayListSCL)
    {
        this.context = context;
        this.arrayListSCL = arrayListSCL;
        arrayListSCLtemp = new ArrayList<>(arrayListSCL);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_scholar_list,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        position = getItemCount() - 1 - position;
        holder.SCLcategory.setText("For " + arrayListSCL.get(position).getCategory() + " students");
        holder.NameOfSCL.setText(arrayListSCL.get(position).getNameOfScholar());
        holder.EligibilityOfSCL.setText(arrayListSCL.get(position).getOneLineEligibility());
        holder.Deadline.setText("till " + arrayListSCL.get(position).getDeadline());
        int serialNo = arrayListSCL.get(position).getSerialNo();
        int finalPosition = position + 1;
        holder.ViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewSCL.class);
                intent.putExtra("SerialNo", Integer.toString(serialNo));
                context.startActivity(intent);

            }
        });



    }





    @Override
    public int getItemCount() {
        return arrayListSCL.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView SCLcategory,NameOfSCL,EligibilityOfSCL,Deadline,ViewMore,CTree;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            SCLcategory = itemView.findViewById(R.id.ScholarShipCategory);
            NameOfSCL = itemView.findViewById(R.id.NameOfScholarShip);
            EligibilityOfSCL = itemView.findViewById(R.id.EligibilityOfScholarShiip);
            Deadline = itemView.findViewById(R.id.Deadline);
            ViewMore = itemView.findViewById(R.id.ViewMore);

        }
    }





}
