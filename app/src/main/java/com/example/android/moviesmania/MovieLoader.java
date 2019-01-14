package com.example.android.moviesmania;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class MovieLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    private String apiURL=null;

    public MovieLoader(@NonNull Context context, String Url) {
        super(context);
        this.apiURL = Url;
    }

    @Nullable
    @Override
    public ArrayList<Movie> loadInBackground() {
        DataUtils dataUtils = new DataUtils();
        ArrayList<Movie> data = dataUtils.makeHttpRequest(apiURL);
        return data;
    }
}
