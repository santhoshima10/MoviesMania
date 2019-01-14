package com.example.android.moviesmania;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{


    private ArrayList<Movie> mMovieData;
    private Context mContext;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MovieAdapter(ArrayList<Movie> mMovieData , Context context) {
        this.mMovieData = mMovieData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
         View movieView = inflater.inflate(R.layout.list_item_view,parent,false);

        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie =mMovieData.get(position);

        ImageView moviePoster = holder.moviePoster;
        TextView movieTitle = holder.movieTitle;
        TextView movieRating = holder.movieRating;

        Log.d("Adapter",movie.getmMoviePosterURL());


        Picasso.with(mContext).load(movie.getmMoviePosterURL()).fit().centerInside().into(moviePoster);
        movieTitle.setText(movie.getmMovieTitle());
        movieRating.setText(movie.getmMovieRatings());

    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       public ImageView moviePoster;
       public TextView  movieTitle;
       public TextView  movieRating;

        public ViewHolder(View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.movie_poster_id);
            movieTitle = itemView.findViewById(R.id.movie_title_id);
            movieRating = itemView.findViewById(R.id.movie_rating_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                       mListener.OnItemClick(position);
                    }
                }
            });
        }
    }
}