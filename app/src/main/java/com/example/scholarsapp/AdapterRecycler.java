package com.example.scholarsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyHolder> {
    Context context;
    String getCurrUID;
    ArrayList<LinearPojo> pojoArrayList;
    public AdapterRecycler(Context context, ArrayList<LinearPojo> pojoArrayList) {
        this.context = context;
        this.pojoArrayList= pojoArrayList;
    }




    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oquery_layout,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
       position=getItemCount()-1-position;
       holder.Title.setText(pojoArrayList.get(position).getTitle());
       holder.Sno.setText("# " +Integer.toString( pojoArrayList.get(position).getSerialNo()));
       holder.DedicatedTo.setText(pojoArrayList.get(position).getDedicatedTo());
       holder.Content.setText(pojoArrayList.get(position).getContent());
       MainActivity.setNameFromUserId(holder.PosterSno,pojoArrayList.get(position).getPosterSerialNo(),holder.ImageUser);
               holder.DateText.setText("(posted on " +pojoArrayList.get(position).getDate() + ")");





    }

    @Override

    public int getItemCount() {
        return pojoArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView Title,DedicatedTo,Content,PosterSno,Sno,DateText,ReactBtn,ReactionNum;
        ImageView ImageUser,Reaction1,Reaction2,Reaction3,Reaction4;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
           Title = itemView.findViewById(R.id.Title);
           DedicatedTo= itemView.findViewById(R.id.DedName);
           Content = itemView.findViewById(R.id.Content);
           Sno = itemView.findViewById(R.id.Sno);
           PosterSno = itemView.findViewById(R.id.NameOfUser);
           ImageUser= itemView.findViewById(R.id.ImageOfUser);
           DateText = itemView.findViewById(R.id.Datetext);

           Reaction1 = itemView.findViewById(R.id.Reaction1);
            Reaction2 = itemView.findViewById(R.id.Reaction2);
            Reaction3 = itemView.findViewById(R.id.Reaction3);
            Reaction4 = itemView.findViewById(R.id.Reaction4);
           // ReactionNum = itemView.findViewById(R.id.ReactionNum);
           // ReactBtn = itemView.findViewById(R.id.ReactBtn);

        }
    }



}
