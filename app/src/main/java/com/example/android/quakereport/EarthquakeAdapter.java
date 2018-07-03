package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        // Get the current object at this position
        Earthquake currentEarthquake = getItem(position);

        // Grab the list_item_mag TextView, then set the text to the current item
        TextView magText = (TextView) listItemView.findViewById(R.id.list_item_mag);
        magText.setText(currentEarthquake.getMagnitude());

        // Grab the list_item_place TextView, then set the text to the current item
        TextView placeText = (TextView) listItemView.findViewById(R.id.list_item_place);
        placeText.setText(currentEarthquake.getPlace());

        // Grab the list_item_date TextView, then set the text to the current item
        TextView dateText = (TextView) listItemView.findViewById(R.id.list_item_date);
        dateText.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
