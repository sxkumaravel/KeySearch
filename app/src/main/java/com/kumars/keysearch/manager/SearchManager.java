package com.kumars.keysearch.manager;


import com.google.gson.Gson;
import com.kumars.keysearch.model.SearchResponse;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Manager to perform network operation in the background thread.
 *
 * @author kumars
 */

@EBean
public class SearchManager {

    @Bean
    RestClient mRestClient;

    /**
     * Method to handle network search call in background thread and to convert the response data to {@link SearchResponse}.
     *
     * @param callback passed by the caller.
     */
    @Background
    public void getSearch(String name, SearchCallback callback) {
        String response = mRestClient.getSearch(name);

        if (response != null) {
            SearchResponse searchResponse = new Gson().fromJson(response, SearchResponse.class);
            callback.onSuccess(searchResponse);
        } else {
            callback.onFailure();
        }
    }

    /**
     * Abstract callback class that will update the caller with specific information
     * network data loading status.
     */
    public static abstract class SearchCallback {
        /**
         * This will be called when anything went wrong in fetching data.
         */
        public abstract void onFailure();

        /**
         * This will we called when the response is loaded successfully.
         *
         * @param searchResponse model with search result information.
         */
        public abstract void onSuccess(SearchResponse searchResponse);
    }
}
