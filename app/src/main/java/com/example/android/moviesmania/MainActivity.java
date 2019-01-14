package com.example.android.moviesmania;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>>, MovieAdapter.OnItemClickListener {

    private static final String baseURL = "http://image.tmdb.org/t/p/";
    private static  final String imageSize = "w185";
    private static final String MOVIETITLE = "MOVIETITLE";
    private static final String MOVIEPOSTERURL = "MOVIEPOSTERURL";
    private static final String MOVIEBACKDROPURL = "MOVIEBACKDROPURL";
    private static final String MOVIEPLOT = "MOVIEPLOT";
    private static final String MOVIERELEASEDATE = "MOVIERELEASEDATE";
    private static final String MOVIERATING = "MOVIERATING";

    private ArrayList<Movie> mData = new ArrayList<>();
    private MovieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MovieAdapter(mData,this);
        recyclerView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(1,null,this).forceLoad();
        adapter.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_id:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Movie> populateTestData(){
        ArrayList<Movie> mMovieData = new ArrayList<>();
        mMovieData.add(new Movie("297802","Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.","","7.6",buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),"2018-10-03",""));
        mMovieData.add(new Movie("297802","Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.","","7.6",buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),"2018-10-03",""));
        mMovieData.add(new Movie("297802","Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.","","7.6",buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),"2018-10-03",""));
        mMovieData.add(new Movie("297802","Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.","","7.6",buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),"2018-10-03",""));
        mMovieData.add(new Movie("297802","Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.","","7.6",buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),"2018-10-03",""));
        mMovieData.add(new Movie("297802","Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.","","7.6",buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),buildImageURL("2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg"),"2018-10-03",""));
        return mMovieData;
    }


    private String buildImageURL(String imagePath){

        String imageURLPath=null;

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
        .authority("image.tmdb.org")
        .appendPath("t")
        .appendPath("p")
        .appendPath(imageSize)
        .appendPath(imagePath)
        ;


             imageURLPath = builder.build().toString();
        Log.d("MainActivity",imageURLPath);
        return imageURLPath;
    }

    private String buildRequestURL(){

        String movieDBAPIURL=null;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sort_by = sharedPreferences.getString(getString(R.string.movie_order_by_key),getString(R.string.movie_order_by_default));

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key","test")
                .appendQueryParameter("language","en-US")
                .appendQueryParameter("sort_by",sort_by)
                .appendQueryParameter("include_adult","false")
                .appendQueryParameter("include_video","false")
                .appendQueryParameter("page","1")

        ;


        movieDBAPIURL = builder.build().toString();
        Log.d("MainActivity",movieDBAPIURL);
        Log.d("MainActivity",sort_by);
        return movieDBAPIURL;
    }



    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        String apiURL = buildRequestURL();
               return new MovieLoader(this,apiURL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
     if(data != null){
         mData.clear();
     }
     mData.addAll(data);
     adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movie>> loader) {

    }

    @Override
    public void OnItemClick(int position) {

        Intent detailIntent = new Intent(MainActivity.this,MovieDetailActivity.class);
        Movie movieData = mData.get(position);
        detailIntent.putExtra(MOVIETITLE,movieData.getmMovieTitle());
        detailIntent.putExtra(MOVIEPOSTERURL,movieData.getmMoviePosterURL());
        detailIntent.putExtra(MOVIEBACKDROPURL,movieData.getmMovieBackdropURL());
        detailIntent.putExtra(MOVIEPLOT,movieData.getmMovieOverview());
        detailIntent.putExtra(MOVIERATING,movieData.getmMovieRatings());
        detailIntent.putExtra(MOVIERELEASEDATE,movieData.getmMovieReleaseDate());

        startActivity(detailIntent);

    }
}
