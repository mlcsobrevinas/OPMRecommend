package com.uplb.mlcsobrevinas.OPMrecommender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.uplb.mlcsobrevinas.OPMrecommender.Common.Common;
import com.uplb.mlcsobrevinas.OPMrecommender.Interface.ItemClickListener;
import com.uplb.mlcsobrevinas.OPMrecommender.Model.Song;
import com.uplb.mlcsobrevinas.OPMrecommender.ViewHolder.SongViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SongList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference songList;

    String CategoryId="";

    FirebaseRecyclerAdapter<Song, SongViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        database = FirebaseDatabase.getInstance();
        songList = database.getReference("Song");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_song);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            CategoryId = getIntent().getStringExtra("CategoryId");
        if(!CategoryId.isEmpty() && CategoryId != null){
            if(Common.isConnectedToInternet(getBaseContext()))
                loadListSong(CategoryId);
            else{
                Toast.makeText(SongList.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }

        private void loadListSong(String categoryId){
            adapter = new FirebaseRecyclerAdapter<Song, SongViewHolder>(Song.class, R.layout.song_item, SongViewHolder.class, songList.orderByChild("MenuId").equalTo(categoryId)) {
                @Override
                protected void populateViewHolder(SongViewHolder viewHolder, Song model, int position) {
                    viewHolder.song_name.setText(model.getName());
                    Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.song_image);

                    final Song local = model;
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(SongList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };

            recyclerView.setAdapter(adapter);
    }

}
