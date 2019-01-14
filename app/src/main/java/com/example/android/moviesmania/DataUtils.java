package com.example.android.moviesmania;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataUtils {


    private URL createURL (String inputURL){

        URL url = null;
        try {
             url = new URL(inputURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public ArrayList<Movie> makeHttpRequest(String url){

        URL movieURL = createURL(url);
        HttpURLConnection httpURLConnection = null;
        ArrayList<Movie> movies = new ArrayList<>();

        try {
             httpURLConnection = (HttpURLConnection) movieURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            String reponseInString = readStreamResponse(inputStream);

            movies = parseJsonResponse(reponseInString);



        }
        catch (IOException io){
            io.printStackTrace();
        }
        finally {
            httpURLConnection.disconnect();
        }

        return movies;

    }

    private String readStreamResponse(InputStream inputStream)  {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null){
                builder.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();

    }

    private String buildImageURL(String imagePath, String imageSize){

        String imageURLPath=null;


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath(imageSize)
                .appendEncodedPath(imagePath)

        ;


        imageURLPath = builder.build().toString();
        Log.d("DataUtils",imageURLPath);
        return imageURLPath;
    }

    private String formatDate(String inputDate){
        if(!inputDate.isEmpty()){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
        Date date=new Date();

        try {
            date = dateFormat.parse(inputDate);
            // Log.v("format",date.toString());
            // Log.v("format",dateFormat1.format(date));



        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat1.format(date);
        }
        else{
            return "N/A";
        }
    }

    private ArrayList<Movie> parseJsonResponse(String jsonResponse) {

        ArrayList<Movie> moviesData = new ArrayList<>();

        if (jsonResponse != null) {

            try {
                JSONObject inputJsonObject = new JSONObject(jsonResponse);

                JSONArray resultArray = inputJsonObject.optJSONArray("results");
                if (resultArray != null) {

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject resultObject = resultArray.optJSONObject(i);
                        if (resultObject != null) {

                            String movieTitle = resultObject.getString("original_title");
                            String movieOverview = resultObject.getString("overview");
                            String movieRating = resultObject.getString("vote_average");
                            String moviePosterPath = resultObject.getString("poster_path");
                            String movieBackdropPath = resultObject.getString("backdrop_path");
                            String movieReleaseDate = resultObject.getString("release_date");
                            String movieID = resultObject.getString("id");

                            Log.d("DataUtils","Movie Title :"+movieTitle);
                            Log.d("DataUtils","movie Overview :"+movieOverview);
                            Log.d("DataUtils","movie Rating :"+movieRating);
                            Log.d("DataUtils","movie PosterPath :"+moviePosterPath);
                            Log.d("DataUtils","movie BackdropPath :"+movieBackdropPath);
                            Log.d("DataUtils","movie ReleaseDate :"+movieReleaseDate);
                            Log.d("DataUtils","movieID :"+movieID);

                            if(movieBackdropPath.isEmpty() || movieBackdropPath == "null"){
                                movieBackdropPath = moviePosterPath;
                            }

                            Movie movieData = new Movie(movieID, movieTitle, movieOverview, null, movieRating, buildImageURL(moviePosterPath,"w185"),buildImageURL(movieBackdropPath,"w500"), formatDate(movieReleaseDate), null);

                            moviesData.add(movieData);

                        }
                    }

                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }


        }

        return moviesData;

    }

}
