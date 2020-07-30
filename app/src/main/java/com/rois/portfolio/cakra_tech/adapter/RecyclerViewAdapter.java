package com.rois.portfolio.cakra_tech.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rois.portfolio.cakra_tech.R;
import com.rois.portfolio.cakra_tech.model.Film;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Film> filmArrayList;

    public RecyclerViewAdapter(Activity context, ArrayList<Film> filmArrayList) {
        this.context = context;
        this.filmArrayList = filmArrayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Film film = filmArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        viewHolder.txtView_title.setText(film.getTitle());
        viewHolder.txtView_description.setText(film.getDescription());
        Glide.with(context).load(film.getImgIcon())
                .into(viewHolder.imgView_icon);
    }

    @Override
    public int getItemCount() {
        return filmArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_icon;
        TextView txtView_title;
        TextView txtView_description;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_icon = itemView.findViewById(R.id.imgView_icon);
            txtView_title = itemView.findViewById(R.id.txtView_title);
            txtView_description = itemView.findViewById(R.id.txtView_description);


        }
    }
}
