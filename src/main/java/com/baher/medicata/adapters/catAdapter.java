package com.baher.medicata.adapters;

import android.graphics.Color;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baher.medicata.R;

import java.util.List;

public class catAdapter extends RecyclerView.Adapter<catAdapter.MyView> {

    List<Integer> pics ;
    List<String> names ;

    public class MyView
            extends RecyclerView.ViewHolder {

        // Text View
        TextView txtCat;
        ImageView imgCat ;
        LinearLayout container ;


        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(View view)
        {
            super(view);

            // initialise TextView with id
            txtCat = (TextView)view
                    .findViewById(R.id.txtCat);
            imgCat = (ImageView) view.findViewById(R.id.imgCat);

            container = (LinearLayout) view.findViewById(R.id.cardView);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public catAdapter(List<Integer> pics, List<String> names)
    {
        this.pics = pics;
        this.names = names;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType)
    {

        // Inflate item.xml using LayoutInflator
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.catogery_item,
                        parent,
                        false);

        // return itemView
        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final MyView holder,
                                 final int position)
    {

        // Set the text of each item of
        // Recycler view with the list items
        holder.txtCat.setText(names.get(position));
       // Picasso.get().load(pics.get(position)).resize(100,100).into(holder.imgCat);
//
        holder.imgCat.setImageResource(pics.get(position));
        if (position == 0 ){
           holder.container.setBackgroundColor(Color.parseColor("#ffffff"));
        }

    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return names.size();
    }
}
