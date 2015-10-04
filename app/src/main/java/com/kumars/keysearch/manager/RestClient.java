package com.kumars.keysearch.manager;

import android.util.Log;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Rest implementation for REST interface.
 *
 * @author kumars
 */
@EBean
public class RestClient {

    private static final String TAG = RestClient.class.getSimpleName();

    @RestService
    RestInterface mRestInterface;

    @AfterInject
    void setUpServices() {
        RestTemplate restTemplate = mRestInterface.getRestTemplate();

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(10000);
        requestFactory.setConnectTimeout(15000);

        restTemplate.setRequestFactory(requestFactory);
    }

    /**
     * Fetch data.
     *
     * @return string.
     */
    public String getSearch(String name) {
        try {
            return mRestInterface.getSearch(name);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
        return null;
    }
}
