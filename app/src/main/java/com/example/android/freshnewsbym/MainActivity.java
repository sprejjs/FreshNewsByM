package com.example.android.freshnewsbym;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String GUARDIAN_API_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    //https://content.guardianapis.com/search?q=travel&api-key=test -> según la documentación del API

    //https://content.guardianapis.com/search?from-date=2017-01-01&order-by=newest
    // &show-fields=headline%2Cbyline%2Cthumbnail&page-size=25&format=json
    // &api-key=5c759d1c-239f-445f-b72b-bfdb2d10b86b
    // mi key con todas las noticias (no puedo solamente elegir una sección porque
    //la sección donde está la noticia es required - no tiene sentido que ponga "travel" si el app
    //es sólo de eso

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an ArrayList of FreshNews objects
        ArrayList<FreshNews> freshNews = QueryUtils.extractFreshNews();

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        FreshNewsAdapter newsAdapter = new FreshNewsAdapter(this, freshNews);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView freshNewsListView = (ListView) findViewById(R.id.fresh_news_list);
        freshNewsListView.setAdapter(newsAdapter);

        // Create a new adapter that takes the list of fresh news as input
        final FreshNewsAdapter adapter = new FreshNewsAdapter(this, freshNews);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        freshNewsListView.setAdapter(adapter);

        freshNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                FreshNews currentFreshNews = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentFreshNews.getURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }
}
