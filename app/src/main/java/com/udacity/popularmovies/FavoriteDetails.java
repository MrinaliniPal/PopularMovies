package com.udacity.popularmovies;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class FavoriteDetails extends Fragment {

    String title,poster,backDrop,userRating,releaseDate,overview;
    TextView favTitle,favUserRating,favReleaseDate,favOverview;
    ImageView favPoster,favBackdrop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favorite_details, container, false);

        favTitle = (TextView)view.findViewById(R.id.favTitle);
        favPoster = (ImageView) view.findViewById(R.id.favPosterPic);
        favBackdrop = (ImageView) view.findViewById(R.id.favBackdrop);
        favUserRating= (TextView)view.findViewById(R.id.favUserRating);
        favReleaseDate = (TextView)view.findViewById(R.id.favReleaseDate);
        favOverview = (TextView)view.findViewById(R.id.favOverview);


        Bundle bundle = getArguments();
        if(bundle != null){
            String movieID = bundle.getString("movieID");
            setfavMovieDetails(movieID);
        }
        return view;
    }

    public void setfavMovieDetails(String movieID)
    {

        Cursor cursor = getActivity().getContentResolver().query(MovieDBContentProvider.CONTENT_URI, null, null, null, null);

        if (!cursor.moveToFirst()) {
            Toast.makeText(getActivity(), " no record found yet!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            do
            {
                int mid = Integer.parseInt(cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_MOVIE_ID)));
                if(mid==Integer.parseInt(movieID))
                {
                    title = cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_TITLE));
                    poster = cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_POSTER));
                    backDrop = cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_BACKDROP));
                    userRating = cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_USER_RATING));
                    releaseDate = cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_RELEASE_DATE));
                    overview = cursor.getString(cursor.getColumnIndex(MovieDBContract.MOVIE_ENTRY.COLUMN_OVERVIEW));

                    favTitle.setText(title);
                    favReleaseDate.setText(releaseDate);
                    favUserRating.setText(userRating);
                    favOverview.setText(overview);
                    Picasso.with(getActivity()).load(poster).into(favPoster);
                    Picasso.with(getActivity()).load(backDrop).into(favBackdrop);

                    break;
                }
            }while (cursor.moveToNext());
        }
    }
}
