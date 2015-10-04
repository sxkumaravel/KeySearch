package com.kumars.keysearch.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kumars
 */
public class ResponseData implements Parcelable {

    @SerializedName("results")
    private List<ResultData> results;

    public List<ResultData> getResults() {
        return results;
    }

    public static class ResultData implements Parcelable {

        @SerializedName("GsearchResultClass")
        private String searchResultClass;

        @SerializedName("unescapedUrl")
        private String unescapedUrl;

        @SerializedName("url")
        private String url;

        @SerializedName("visibleUrl")
        private String visibleUrl;

        @SerializedName("cacheUrl")
        private String cacheUrl;

        @SerializedName("title")
        private String title;

        @SerializedName("titleNoFormatting")
        private String content;

        public String getSearchResultClass() {
            return searchResultClass;
        }

        public String getUnescapedUrl() {
            return unescapedUrl;
        }

        public String getUrl() {
            return url;
        }

        public String getVisibleUrl() {
            return visibleUrl;
        }

        public String getCacheUrl() {
            return cacheUrl;
        }

        public String getTitle() {
            return Html.fromHtml(title).toString();
        }

        public String getContent() {
            return Html.fromHtml(content).toString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.searchResultClass);
            dest.writeString(this.unescapedUrl);
            dest.writeString(this.url);
            dest.writeString(this.visibleUrl);
            dest.writeString(this.cacheUrl);
            dest.writeString(this.title);
            dest.writeString(this.content);
        }

        public ResultData() {
        }

        protected ResultData(Parcel in) {
            this.searchResultClass = in.readString();
            this.unescapedUrl = in.readString();
            this.url = in.readString();
            this.visibleUrl = in.readString();
            this.cacheUrl = in.readString();
            this.title = in.readString();
            this.content = in.readString();
        }

        public static final Creator<ResultData> CREATOR = new Creator<ResultData>() {
            public ResultData createFromParcel(Parcel source) {
                return new ResultData(source);
            }

            public ResultData[] newArray(int size) {
                return new ResultData[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
    }

    public ResponseData() {
    }

    protected ResponseData(Parcel in) {
        this.results = new ArrayList<ResultData>();
        in.readList(this.results, ResultData.class.getClassLoader());
    }

    public static final Creator<ResponseData> CREATOR = new Creator<ResponseData>() {
        public ResponseData createFromParcel(Parcel source) {
            return new ResponseData(source);
        }

        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
        }
    };
}
