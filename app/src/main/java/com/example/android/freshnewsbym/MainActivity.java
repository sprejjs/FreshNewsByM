package com.example.android.freshnewsbym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
