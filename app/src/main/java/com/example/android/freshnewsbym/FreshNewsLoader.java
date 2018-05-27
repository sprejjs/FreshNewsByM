package com.example.android.freshnewsbym;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */

public class FreshNewsLoader extends AsyncTaskLoader<List<FreshNews>> {

    /** Tag for log messages */
    private static final String LOG_TAG = FreshNewsLoader.class.getName();

    /** Query URL */
    private String vUrl;

    /**
     * Constructs a new {@link FreshNewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public FreshNewsLoader(Context context, String url) {
        super(context);
        vUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<FreshNews> loadInBackground() {
        if (vUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        List<FreshNews> news = QueryUtils.fetchNewsData(vUrl);
        return news;
    }
}
