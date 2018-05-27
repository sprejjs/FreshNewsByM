package com.example.android.freshnewsbym;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URL;
import java.util.ArrayList;

public class FreshNewsAdapter extends ArrayAdapter<FreshNews> {

    private static final String LOG_TAG = FreshNewsAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param freshNews      A List of FreshNews objects to display in a list
     */
    public FreshNewsAdapter(Activity context, ArrayList<FreshNews> freshNews) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, freshNews);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fresh_news_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        FreshNews currentFreshNews = getItem(position);

        ImageView newsPhoto = (ImageView) listItemView.findViewById(R.id.news_photo);
        newsPhoto.setImageResource(currentFreshNews.getThumbnail());
        //BUSCAR LA FORMA DE DESPLEGAR LA FOTO DEL URL

        TextView headline = (TextView) listItemView.findViewById(R.id.headline);
        headline.setText(currentFreshNews.getHeadline());

        TextView byline = (TextView) listItemView.findViewById(R.id.byline);
        byline.setText(currentFreshNews.getByline());

        TextView datePublished = (TextView) listItemView.findViewById(R.id.date_published);
        datePublished.setText(currentFreshNews.getDatePublished());

        TextView sectionName = (TextView) listItemView.findViewById(R.id.section);
        sectionName.setText(currentFreshNews.getSectionName());


        // Return the whole list item layout
        return listItemView;
    }
}
