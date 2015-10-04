package com.kumars.keysearch.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author kumars
 */

public class SearchResponse implements Parcelable {

    @SerializedName("responseData")
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.responseData, 0);
    }

    public SearchResponse() {
    }

    protected SearchResponse(Parcel in) {
        this.responseData = in.readParcelable(ResponseData.class.getClassLoader());
    }

    public static final Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {
        public SearchResponse createFromParcel(Parcel source) {
            return new SearchResponse(source);
        }

        public SearchResponse[] newArray(int size) {
            return new SearchResponse[size];
        }
    };
}
