package com.baher.medicata.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.baher.medicata.DoctorActivity;
import com.baher.medicata.InstitutionsOnMap;
import com.baher.medicata.MainActivity;
import com.baher.medicata.R;
import com.baher.medicata.models.main.Data;
import com.skydoves.transformationlayout.TransformationCompat;
import com.skydoves.transformationlayout.TransformationLayout;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class docAdapter extends RecyclerView.Adapter<docAdapter.MyView>{

    List<Data> doctors ;
    Context context ;
    boolean vertical ;


    public static Data choosenDoctor ;


    public class MyView
            extends RecyclerView.ViewHolder {

        // Text View
        TextView txtName , txtSpec;
        ImageView imgAvatar ;
        FrameLayout frameLayout ;
        TransformationLayout transformationLayout ;
        CardView cardView ;
        int click = 0 ;

        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(View view)
        {
            super(view);

            // initialise TextView with id
            txtName = (TextView)view
                    .findViewById(R.id.txtName);
            txtSpec = (TextView)view.findViewById(R.id.txtSpec);
            imgAvatar = (ImageView)view.findViewById(R.id.imgAvatar);
           // doctors = new ArrayList<>();
            frameLayout = (FrameLayout)view.findViewById(R.id.frameLayout);
            transformationLayout = (TransformationLayout)view.findViewById(R.id.transformationLayout);
            cardView = (CardView)view.findViewById(R.id.cardView);

        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public docAdapter(Context context,List<Data> doctors,boolean vertical)
    {
        this.context = context ;
        this.doctors = doctors ;
        this.vertical = vertical ;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public docAdapter.MyView onCreateViewHolder(ViewGroup parent,
                                                 int viewType)
    {

        // Inflate item.xml using LayoutInflator
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.doctor_item,
                        parent,
                        false);

        // return itemView
        return new docAdapter.MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        // Set the text of each item of
        // Recycler view with the list items


        holder.txtName.setText(doctors.get(position).getInstitutionTitle());
        holder.txtSpec.setText(doctors.get(position).getSpecialty());
        String imgUrl = doctors.get(position).getImage();
        Picasso.get().load(imgUrl).into(holder.imgAvatar);
        if (position == 0 && vertical ){
            holder.frameLayout.setPadding(0,130,0,0);

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vertical) {
                    choosenDoctor = doctors.get(position);
                    Intent intent = new Intent(context, DoctorActivity.class);
                    //             intent.putExtra("doctor", (Data) doctors.get(position));
//                intent.putExtra("doctor", (Parcelable) doctors.get(position));
                    TransformationCompat.startActivity(holder.transformationLayout, intent);
                }

                if (!vertical){
                    if (holder.click==0){
                        InstitutionsOnMap.moveMap(doctors.get(position).getLat(),doctors.get(position).getLng());
                        holder.click ++ ;
                    }else {
                        choosenDoctor = doctors.get(position);
                        holder.click = 0 ;
                        Intent intent = new Intent(context, DoctorActivity.class);
                        //             intent.putExtra("doctor", (Data) doctors.get(position));
//                intent.putExtra("doctor", (Parcelable) doctors.get(position));
                        TransformationCompat.startActivity(holder.transformationLayout, intent);
                    }
                }
            }
        });

    }




    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return doctors.size();
    }
}




