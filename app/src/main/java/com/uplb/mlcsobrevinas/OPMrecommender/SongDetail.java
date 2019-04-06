package com.uplb.mlcsobrevinas.OPMrecommender;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.uplb.mlcsobrevinas.OPMrecommender.Common.Common;
import com.uplb.mlcsobrevinas.OPMrecommender.Model.Rating;
import com.uplb.mlcsobrevinas.OPMrecommender.Model.Song;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SongDetail extends AppCompatActivity implements RatingDialogListener {

    TextView band_name, band_description;
    ImageView band_image;
    CollapsingToolbarLayout collapsingToolbarLayout;

    FloatingActionButton btnRating;
    RatingBar ratingBar;

    String bandId="";

    FirebaseDatabase database;
    DatabaseReference bands;

    DatabaseReference ratingTbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        database = FirebaseDatabase.getInstance();
        bands = database.getReference("Bands");

        ratingTbl = database.getReference("Rating");

        btnRating = (FloatingActionButton)findViewById(R.id.btn_rating);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);


        btnRating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showRatingDialog();

            }
        });

        band_description = (TextView)findViewById(R.id.band_description);
        band_image = (ImageView)findViewById(R.id.img_song);
        band_name = (TextView)findViewById(R.id.song_name);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        if(getIntent() != null)
            bandId = getIntent().getStringExtra("BandsId");
        if(!bandId.isEmpty()){
            if(Common.isConnectedToInternet(getBaseContext())) {
                getDetailBand(bandId);
                getRatingBand(bandId);
            }
            else{
                Toast.makeText(SongDetail.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void getRatingBand(String bandId) {
        Query bandRating = ratingTbl.child(Common.currentUser.getPhone()).child(bandId).child("rateValue");
       // Query counter = ratingTbl.orderByChild("bandId").equalTo(bandId);

        bandRating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //if (!dataSnapshot.exists()){
                  try {
                      String ratingValue = (String) dataSnapshot.getValue();
                      Integer rating = Integer.valueOf(ratingValue);
                      ratingBar.setRating(rating);
                  } catch(Exception e){

                        }
                    }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showRatingDialog() {
               new AppRatingDialog.Builder()
                       .setPositiveButtonText("Submit")
                       .setNegativeButtonText("Cancel")
                       .setNoteDescriptions(Arrays.asList("Very Bad","Not Good", "Quite Okay", "Very Good", "Excellent"))
                       .setDefaultRating(1)
                       .setTitle("Rate this band")
                       .setDescription("Please select rating")
                       .setTitleTextColor(R.color.colorPrimary)
                       .setDescriptionTextColor(R.color.colorPrimary)
                       .setHint("Please write your comment here")
                       .setHintTextColor(R.color.colorAccent)
                       .setCommentTextColor(android.R.color.white)
                       .setCommentBackgroundColor(R.color.colorPrimaryDark)
                       .setWindowAnimation(R.style.RatingDialogFadeAnim)
                       .create(SongDetail.this)
                       .show();
    }

    private void getDetailBand(String bandId) {
        bands.child(bandId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      Song band = dataSnapshot.getValue(Song.class);
                      Picasso.with(getBaseContext()).load(band.getImage()).into(band_image);
                      collapsingToolbarLayout.setTitle(band.getName());
                      band_name.setText(band.getName());
                      band_description.setText(band.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onNegativeButtonClicked() {
        finish();
    }

    @Override
    public void onNeutralButtonClicked() {
        finish();
    }

    @Override
    public void onPositiveButtonClicked(int value, @NotNull String comments) {

      final Rating rating = new Rating(Common.currentUser.getPhone(), bandId, String.valueOf(value), comments);
      //i replaced addValueEventListener to addListenerForSingleValueEvent
        ratingTbl.child(Common.currentUser.getPhone()).child(bandId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(Common.currentUser.getPhone()).child(bandId).exists()){
                    ratingTbl.child(Common.currentUser.getPhone()).child(bandId).removeValue();
                    ratingTbl.child(Common.currentUser.getPhone()).child(bandId).setValue(rating);
                    Toast.makeText(SongDetail.this, "Thank you for your rating!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    ratingTbl.child(Common.currentUser.getPhone()).child(bandId).setValue(rating);
                    Toast.makeText(SongDetail.this, "Thank you for your rating!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                finish();
            }

        });
    }
}
