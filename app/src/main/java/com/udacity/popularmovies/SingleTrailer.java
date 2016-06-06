package com.udacity.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Mrinalini galle on 06-06-2016.
 */
public class SingleTrailer extends ArrayAdapter<String> {

    String[] TrailerTitle;
    Activity context;
    TextView textView;

    public SingleTrailer(Activity context, String[] TrailerTitle) {
        super(context,R.layout.single_trailer,TrailerTitle);
        this.context=context;
        this.TrailerTitle=TrailerTitle;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.single_trailer, null, true);

        textView=(TextView)rowView.findViewById(R.id.trailerTextView);
        textView.setText(TrailerTitle[position]);

        return rowView;
    }
}

