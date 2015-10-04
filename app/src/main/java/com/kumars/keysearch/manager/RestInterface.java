package com.kumars.keysearch.manager;


import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @author kumars
 */

@Rest(rootUrl = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=",
        converters = {StringHttpMessageConverter.class, GsonHttpMessageConverter.class})
public interface RestInterface extends RestClientRootUrl, RestClientSupport, RestClientHeaders {

    @Get("{name}")
    String getSearch(String name);
}
