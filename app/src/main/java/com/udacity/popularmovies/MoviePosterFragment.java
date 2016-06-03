package com.udacity.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MoviePosterFragment extends Fragment {

    RequestQueue requestQueue;
    String[] moviePosters;
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, mainurl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movieDetail = response.getJSONArray("results");
                    moviePosters = new String[movieDetail.length()];
                    final String[] movieID = new String[movieDetail.length()];
                    final String[] title = new String[movieDetail.length()];

                    for(int i=0;i<movieDetail.length();i++)
                    {
                        movieID[i]=movieDetail.getJSONObject(i).getString("id");
                        title[i]=movieDetail.getJSONObject(i).getString("title");
                        moviePosters[i]=Utils.BASE_PICTURE_URL+Utils.PICTURE_SIZE1+movieDetail.getJSONObject(i).getString("poster_path");

                        MovieListImplement adapter = new MovieListImplement(getActivity(),moviePosters);
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if(MainActivity.isSinglePane)
                                {

                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getActivity(), "Internet not working", Toast.LENGTH_SHORT).show();
            }
    });
    }
}
