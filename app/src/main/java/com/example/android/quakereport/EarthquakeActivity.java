/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    // URL for earthquake data
    private final static String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    // Loader ID
    private final static int EARTHQUAKE_LOADER_ID = 1;

    // The empty text view
    TextView emptyStateTextView;

    //The progress bar
    ProgressBar progressBar;

    private EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes
        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the ListView
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Find the current earthquake that was clicked
                Earthquake currentEarthquake = adapter.getItem(i);

                // Convert the string to a URI object
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Launch the URI intent
                startActivity(websiteIntent);
            }
        });

        // Grab the progress bar to hide it later
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // Set the empty state for the view
        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(emptyStateTextView);

        // Get the connectivity service to see if we're connected to the internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there's internet connectivity then load the data, otherwise throw error message
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            // Grab instance of the LoaderManager and initialize the loader
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            // Hide the progress bar and show no internet message
            progressBar.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // Hide the progress bar
        progressBar.setVisibility(View.GONE);

        // Clear previous earthquake data
        adapter.clear();

        // If earthquakes isn't empty or null then set the adapter with the earthquake data
        if (earthquakes != null && !earthquakes.isEmpty()) adapter.addAll(earthquakes);
        else emptyStateTextView.setText(R.string.no_earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        // Clear out existing data
        adapter.clear();
    }
}
