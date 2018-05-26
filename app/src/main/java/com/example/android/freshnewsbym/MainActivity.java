package com.example.android.freshnewsbym;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    /**
     * URL for the news from The Guardian
     */
    private static final String GUARDIAN_API_REQUEST_URL =
            "https://content.guardianapis.com/search?from-date=2017-01-01&order-by=newest" +
                    "&show-fields=headline%2Cbyline%2Cthumbnail&page-size=25&format=json" +
                    "&api-key=test";


    /** Adapter for the list of newss */
    private FreshNewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the AsyncTask to fetch the news data
        FreshNewsAsyncTask task = new FreshNewsAsyncTask();
        task.execute(GUARDIAN_API_REQUEST_URL);

        // Create an ArrayList of FreshNews objects
        //ArrayList<FreshNews> freshNews = QueryUtils.extractFreshNews();

        // Create a new adapter that takes an empty list of news as input
        newsAdapter = new FreshNewsAdapter(this, new ArrayList<FreshNews>());

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView freshNewsListView = (ListView) findViewById(R.id.fresh_news_list);
        freshNewsListView.setAdapter(newsAdapter);

        freshNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current news that was clicked on
                FreshNews currentFreshNews = newsAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri freshNewsUri = Uri.parse(currentFreshNews.getURL());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, freshNewsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    private class FreshNewsAsyncTask extends AsyncTask<String, Void, List<FreshNews>> {

        @Override
        protected List<FreshNews> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<FreshNews> result = QueryUtils.fetchNewsData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<FreshNews> data) {
            // Clear the adapter of previous news data
            newsAdapter.clear();

            // If there is a valid list of {@link FreshNews}, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                newsAdapter.addAll(data);
            }

        }
    }
}
