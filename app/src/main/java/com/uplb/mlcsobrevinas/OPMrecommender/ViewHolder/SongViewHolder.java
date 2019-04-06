package com.uplb.mlcsobrevinas.OPMrecommender.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uplb.mlcsobrevinas.OPMrecommender.Interface.ItemClickListener;
import com.uplb.mlcsobrevinas.OPMrecommender.R;

public class SongViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView song_name;
    public ImageView song_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SongViewHolder(@NonNull View itemView) {
        super(itemView);
        song_name = (TextView)itemView.findViewById(R.id.song_name);
        song_image = (ImageView)itemView.findViewById(R.id.song_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}
