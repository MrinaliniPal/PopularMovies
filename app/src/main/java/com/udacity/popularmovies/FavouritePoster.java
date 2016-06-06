package com.udacity.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class FavouritePoster extends Fragment {

    GridView gridView;
    String movieID[],poster[],title[];

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_favourite_poster,container,false);
        setHasOptionsMenu(true);

        gridView=(GridView)view.findViewById(R.id.gridViewFav);
        //fetchFav();

        return view;
    }

}
