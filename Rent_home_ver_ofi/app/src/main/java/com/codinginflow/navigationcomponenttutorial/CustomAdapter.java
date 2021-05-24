package com.codinginflow.navigationcomponenttutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList ogloszenie_id,ogloszenie_title,ogloszenie_price,ogloszenie_description;

    CustomAdapter(Context context,
                  ArrayList ogloszenie_id,
                  ArrayList ogloszenie_title,
                  ArrayList ogloszenie_price,
                  ArrayList ogloszenie_description)
    {
        this.context=context;
        this.ogloszenie_description=ogloszenie_description;
        this.ogloszenie_id=ogloszenie_id;
        this.ogloszenie_price=ogloszenie_price;
        this.ogloszenie_title=ogloszenie_title;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_row_ogloszenie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tekstCena.setText(String.valueOf(ogloszenie_price.get(position)));
        holder.tekstTitle.setText(String.valueOf(ogloszenie_title.get(position)));


    }

    @Override
    public int getItemCount() {
        return ogloszenie_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tekstCena,tekstTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tekstTitle=itemView.findViewById(R.id.tekstTitle);
            tekstCena=itemView.findViewById(R.id.tekstCena);

        }
    }
}
