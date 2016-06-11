package com.udacity.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Mrinalini galle on 03-06-2016.
 */
public class MovieListImplement extends ArrayAdapter<String> {

    private final Activity activity;
    private final String[] moviePoster;


    MovieListImplement(Activity activity,String[] moviePoster) {
        super(activity,R.layout.single_poster,moviePoster);
        this.activity=activity;
        this.moviePoster=moviePoster;
    }

    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.single_poster,null,true);

        ImageView imageView = (ImageView)rowView.findViewById(R.id.singlePoster);
        Picasso.with(activity).load(moviePoster[i]).into(imageView);

        return rowView;
    }
}
