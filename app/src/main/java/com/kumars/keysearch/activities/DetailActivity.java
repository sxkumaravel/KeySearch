package com.kumars.keysearch.activities;

import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;
import android.widget.TextView;

import com.kumars.keysearch.R;
import com.kumars.keysearch.model.ResponseData.ResultData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Activity to show the selected search details and an option to email the search URL.
 *
 * @author kumars
 */
@EActivity(R.layout.activity_detail)
public class DetailActivity extends BaseActivity {

    private static final String ROOT_URL = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

    @ViewById(R.id.tv_title)
    TextView mTitle;

    @ViewById(R.id.tv_search_result_class)
    TextView mSearchResult;

    @ViewById(R.id.tv_unescaped_url)
    TextView mUnescapedURL;

    @ViewById(R.id.tv_url)
    TextView mURL;

    @ViewById(R.id.tv_visible_url)
    TextView mVisibleURL;

    @ViewById(R.id.tv_cached_url)
    TextView mCachedURL;

    @ViewById(R.id.tv_content)
    TextView mContent;

    @ViewById(R.id.enter_email)
    EditText mEnterEmail;

    @Extra
    ResultData mResultData;

    @Extra
    String mSearchKey;

    @AfterViews
    void afterViews() {
        if (mResultData != null) {

            mTitle.setText(mResultData.getTitle());
            mSearchResult.setText(mResultData.getSearchResultClass());
            mUnescapedURL.setText(mResultData.getUnescapedUrl());
            mURL.setText(mResultData.getUrl());
            mVisibleURL.setText(mResultData.getVisibleUrl());
            mCachedURL.setText(mResultData.getCacheUrl());
            mContent.setText(mResultData.getContent());
        }
    }

    @Click(R.id.send_email_button)
    protected void sendEmail() {
        sendEmailFromKeySearch();
    }

    // creates implicit intent to start an email client.
    private void sendEmailFromKeySearch() {
        if (mEnterEmail != null && mEnterEmail.getText().toString().length() != 0) {
            String[] TO = {mEnterEmail.getText().toString()};
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "KeySearch Result");
            emailIntent.putExtra(Intent.EXTRA_TEXT, ROOT_URL + mSearchKey);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                showLongToast("Email Created...");
            } catch (android.content.ActivityNotFoundException ex) {
                showLongToast("There is no email client installed.");
            }

        } else {
            showLongToast("Please enter an email id");
        }
    }
}
