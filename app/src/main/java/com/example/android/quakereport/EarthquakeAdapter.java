package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        final Earthquake currentEarthquake = getItem(position);

        // Set onclick listener for the list item, so it opens the URL from the parsed data
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(currentEarthquake.getUrl()));
                getContext().startActivity(intent);
            }
        });

        // Grab the list_item_mag TextView, then set the text to the current item
        TextView magText = (TextView) listItemView.findViewById(R.id.list_item_mag);
        magText.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        // Get the background from the TextView, which is GradientDrawable
        GradientDrawable magCircle = (GradientDrawable) magText.getBackground();
        magCircle.setColor(ContextCompat.getColor(getContext(), getMagnitudeColor(currentEarthquake.getMagnitude())));

        // Grab the list_item_place TextView, then set the text to the current item
        TextView placeText1 = (TextView) listItemView.findViewById(R.id.list_item_place1);
        TextView placeText2 = (TextView) listItemView.findViewById(R.id.list_item_place2);

        // Grab the string from the currentEarthquake and store in place
        String place = currentEarthquake.getPlace();

        // If the place contains "of" split into 2 parts, otherwise just keep it whole
        if (place.contains("of")) {
            String[] stringParts = place.split("(?<=of)");
            placeText1.setText(stringParts[0]);
            placeText2.setText(stringParts[1]);
        } else {
            placeText1.setText(R.string.near_the);
            placeText2.setText(place);
        }

        // Grab the date from getTimeInMilliseconds
        Date date = new Date(currentEarthquake.getTimeInMilliseconds());

        // Grab the list_item_date TextView, then set the text to the date
        TextView dateText = (TextView) listItemView.findViewById(R.id.list_item_date);
        dateText.setText(formatDate(date));

        // Grab the list_item_time TextView, then set the text to the time
        TextView timeText = (TextView) listItemView.findViewById(R.id.list_item_time);
        timeText.setText(formatTime(date));

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string using 1 decimal place
     */
    private String formatMagnitude(double magnitude) {
        // New decimal formatter to format the magnitude to 1 decimal place
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    /**
     * Return the gradient color based on the magnitude
     */
    private int getMagnitudeColor(double magnitude) {
        int color;
        int magRounded = (int) Math.floor(magnitude);

        switch (magRounded) {
            case 0:
                color = R.color.magnitude1;
                break;
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
                break;
            case 6:
                color = R.color.magnitude6;
                break;
            case 7:
                color = R.color.magnitude7;
                break;
            case 8:
                color = R.color.magnitude8;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            default:
                color = R.color.magnitude10plus;
                break;
        }

        return color;
    }
}
