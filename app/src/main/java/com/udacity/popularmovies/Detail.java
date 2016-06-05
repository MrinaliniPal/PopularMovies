package com.udacity.popularmovies;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail extends Fragment {

    RequestQueue requestQueue,requestQueue1,requestQueue2;
    private int movieid;
    private String type;
    String[] ReviewBy, Review;
    String posterpath,BackdropPath;

    private ListView listViewReview,listViewTrailer;
    private ImageView Poster,Backdrop;
    private TextView Title,UserRating,ReleaseDate,Overview;

    private boolean mFavorited;

    public Detail() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){
            movieid = Integer.parseInt(bundle.getString("movieID"));
            type = bundle.getString("movieType");
        }

        listViewTrailer=(ListView)view.findViewById(R.id.listViewTrailer);
        listViewReview=(ListView)view.findViewById(R.id.listViewReview);

        Title=(TextView)view.findViewById(R.id.Title);
        UserRating=(TextView)view.findViewById(R.id.UserRating);
        ReleaseDate=(TextView)view.findViewById(R.id.ReleaseDate);
        Overview=(TextView)view.findViewById(R.id.OverView);
        Poster=(ImageView)view.findViewById(R.id.PosterPic);
        Backdrop=(ImageView)view.findViewById(R.id.BackDrop);

        requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue1= Volley.newRequestQueue(getActivity());
        requestQueue2= Volley.newRequestQueue(getActivity());


        return view;
    }


}
