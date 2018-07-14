package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    // Tag for log messages
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String url;

    /**
     * Constructs a new {@link EarthquakeLoader}
     *
     * @param context context of the activity
     * @param url     url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // Background thread
    @Override
    public List<Earthquake> loadInBackground() {
        if (url == null) return null;

        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(url);
        return earthquakes;
    }
}
