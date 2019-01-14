package com.example.android.moviesmania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String MOVIETITLE = "MOVIETITLE";
    private static final String MOVIEPOSTERURL = "MOVIEPOSTERURL";
    private static final String MOVIEBACKDROPURL = "MOVIEBACKDROPURL";
    private static final String MOVIEPLOT = "MOVIEPLOT";
    private static final String MOVIERELEASEDATE = "MOVIERELEASEDATE";
    private static final String MOVIERATING = "MOVIERATING";

    private ImageView moviePoster;
    private ImageView movieBackdrop;
    private TextView  movieTitle;
    private TextView moviePlot;
    private TextView movieReleaseDate;
    private TextView movieRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar_detail_id);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra(MOVIETITLE));


        moviePoster = findViewById(R.id.detail_movie_poster_id);
        Picasso.with(this).load(getIntent().getStringExtra(MOVIEPOSTERURL)).fit().centerInside().into(moviePoster);

        movieBackdrop = findViewById(R.id.detail_movie_backdrop_id);
        Picasso.with(this).load(getIntent().getStringExtra(MOVIEBACKDROPURL)).fit().centerInside().into(movieBackdrop);

        movieTitle = findViewById(R.id.detail_movie_title_id);
        movieTitle.setText(getIntent().getStringExtra(MOVIETITLE));


        movieReleaseDate = findViewById(R.id.detail_movie_release_date_id);
        movieReleaseDate.setText(getIntent().getStringExtra(MOVIERELEASEDATE));

        movieRating = findViewById(R.id.detail_movie_rating_id);
        movieRating.setText(getIntent().getStringExtra(MOVIERATING));


        moviePlot = findViewById(R.id.detail_movie_plot_id);
        moviePlot.setMovementMethod(new ScrollingMovementMethod());
        moviePlot.setText(getIntent().getStringExtra(MOVIEPLOT));





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
