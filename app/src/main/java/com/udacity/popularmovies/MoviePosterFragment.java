package com.udacity.popularmovies;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    String[] movieposters;
    GridView gridView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_poster, container, false);
        setHasOptionsMenu(true);

        gridView=(GridView)view.findViewById(R.id.gridview);

        requestQueue = Volley.newRequestQueue(getActivity());
        MovieDetailReceive("popular");

        return view;
    }

    public void MovieDetailReceive(final String parturl)
    {

        final String mainurl= Utils.BASE_MOVIE_URL+parturl+Utils.API_KEY;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, mainurl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray movieDetail=response.getJSONArray("results");
                    movieposters= new String[movieDetail.length()];
                    final String[] movieID = new String[movieDetail.length()];
                    final String[] Title = new String[movieDetail.length()];
                    for(int i=0;i<movieDetail.length();i++)
                    {
                        movieID[i] = movieDetail.getJSONObject(i).getString("id");
                        Title[i] = movieDetail.getJSONObject(i).getString("original_title");
                        movieposters[i]=Utils.BASE_PICTURE_URL+Utils.PICTURE_SIZE1+movieDetail.getJSONObject(i).getString("poster_path");
                    }

                    MovieListImplement adapter = new MovieListImplement(getActivity(),movieposters);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            if(MainActivity.isSinglePane)
                            {
                                Detail detail = new Detail();
                                Bundle bundle = new Bundle();
                                bundle.putString("movieID",movieID[position]);
                                bundle.putString("movieType",parturl);
                                detail.setArguments(bundle);
                                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.phone_container,detail);
                                fragmentTransaction.isAddToBackStackAllowed();
                                fragmentTransaction.addToBackStack("movieDetail");
                                fragmentTransaction.commit();
                            }
                            else
                            {
                                Detail detail = new Detail();
                                Bundle bundle = new Bundle();
                                bundle.putString("movieID",movieID[position]);
                                bundle.putString("movieType",parturl);
                                detail.setArguments(bundle);
                                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.detail_fragment, detail);
                                fragmentTransaction.commit();
                            }
                        }
                    });


                }
                catch (JSONException e)
                {
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getActivity(), "Internet Not Available", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.Popular) {
            MovieDetailReceive("popular");
            return true;
        }
        else if (id == R.id.TopRated) {
            MovieDetailReceive("top_rated");
            return true;
        } /*else if (id == R.id.Favourite)
            startActivity(new Intent(getActivity(), Favourite.class));*/
        return super.onOptionsItemSelected(item);
    }
}
