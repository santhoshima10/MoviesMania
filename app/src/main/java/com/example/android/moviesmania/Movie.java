package com.example.android.moviesmania;

public class Movie {

    public String getmMovieID() {
        return mMovieID;
    }

    public void setmMovieID(String mMovieID) {
        this.mMovieID = mMovieID;
    }

    private String mMovieID;
    private String mMovieTitle;
    private String mMovieOverview;
    private String mMoviePlot;
    private String mMovieRatings;
    private String mMoviePosterURL;
    private String mMovieBackdropURL;
    private String mMovieReleaseDate;
    private String mMovieCrewDetails;

    public String getmMovieTitle() {
        return mMovieTitle;
    }

    public void setmMovieTitle(String mMovieTitle) {
        this.mMovieTitle = mMovieTitle;
    }

    public String getmMovieOverview() {
        return mMovieOverview;
    }

    public void setmMovieOverview(String mMovieOverview) {
        this.mMovieOverview = mMovieOverview;
    }

    public String getmMoviePlot() {
        return mMoviePlot;
    }

    public void setmMoviePlot(String mMoviePlot) {
        this.mMoviePlot = mMoviePlot;
    }

    public String getmMovieRatings() {
        return mMovieRatings;
    }

    public void setmMovieRatings(String mMovieRatings) {
        this.mMovieRatings = mMovieRatings;
    }

    public String getmMoviePosterURL() {
        return mMoviePosterURL;
    }

    public void setmMoviePosterURL(String mMoviePosterURL) {
        this.mMoviePosterURL = mMoviePosterURL;
    }

    public String getmMovieReleaseDate() {
        return mMovieReleaseDate;
    }

    public void setmMovieReleaseDate(String mMovieReleaseDate) {
        this.mMovieReleaseDate = mMovieReleaseDate;
    }

    public String getmMovieCrewDetails() {
        return mMovieCrewDetails;
    }

    public void setmMovieCrewDetails(String mMovieCrewDetails) {
        this.mMovieCrewDetails = mMovieCrewDetails;
    }

    public String getmMovieBackdropURL() {
        return mMovieBackdropURL;
    }

    public void setmMovieBackdropURL(String mMovieBackdropURL) {
        this.mMovieBackdropURL = mMovieBackdropURL;
    }

    public Movie(String mMovieID,String mMovieTitle, String mMovieOverview, String mMoviePlot, String mMovieRatings, String mMoviePosterURL, String mMovieBackdropURL,String mMovieReleaseDate, String mMovieCrewDetails) {

        this.mMovieID = mMovieID;
        this.mMovieTitle = mMovieTitle;
        this.mMovieOverview = mMovieOverview;
        this.mMoviePlot = mMoviePlot;
        this.mMovieRatings = mMovieRatings;
        this.mMoviePosterURL = mMoviePosterURL;
        this.mMovieBackdropURL = mMovieBackdropURL;
        this.mMovieReleaseDate = mMovieReleaseDate;
        this.mMovieCrewDetails = mMovieCrewDetails;

    }
}
