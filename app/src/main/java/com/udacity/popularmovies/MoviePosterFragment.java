package com.udacity.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MoviePosterFragment extends Fragment {

    RequestQueue requestQueue;
    String[] movieposters;
    GridView gridView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster,container,false);
        setHasOptionsMenu(true);

        gridView = (GridView)view.findViewById(R.id.gridview);

        requestQueue = Volley.newRequestQueue(getActivity());
        movieDetailReceive("popular");

        return view;
    }

    public void movieDetailReceive(final String parturl)
    {
        String mainurl = Utils.BASE_MOVIE_URL+parturl+Utils.API_KEY;
    }
}
