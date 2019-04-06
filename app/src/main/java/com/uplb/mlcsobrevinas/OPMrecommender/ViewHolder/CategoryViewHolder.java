package com.uplb.mlcsobrevinas.OPMrecommender.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.uplb.mlcsobrevinas.OPMrecommender.Interface.ItemClickListener;
import com.uplb.mlcsobrevinas.OPMrecommender.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView category_image;

    private ItemClickListener itemClickListener;

    public CategoryViewHolder(View itemView){
        super(itemView);
        category_image = (ImageView)itemView.findViewById(R.id.category_image);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public void onClick(View view){
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
