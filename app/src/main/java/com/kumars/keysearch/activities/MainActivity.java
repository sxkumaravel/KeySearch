package com.kumars.keysearch.activities;

import android.widget.EditText;

import com.kumars.keysearch.R;
import com.kumars.keysearch.manager.SearchManager;
import com.kumars.keysearch.model.SearchResponse;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Activity to provide UI to type a key work to search.
 *
 * @author kumars
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Bean
    SearchManager mSearchManager;

    @ViewById(R.id.editText)
    EditText mEditText;

    @Click(R.id.button_search)
    protected void onSearchClicked() {
        hideSoftKey();

        if (mEditText != null && mEditText.getText().toString().length() != 0) {
            showLoading();
            mSearchManager.getSearch(mEditText.getText().toString(), new SearchManager.SearchCallback() {
                @Override
                public void onFailure() {
                    hideLoading();
                    showLongToast("Failure");
                }

                @Override
                public void onSuccess(SearchResponse searchResponse) {
                    hideLoading();
                    showLongToast("Success");
                    navigateToListActivity(searchResponse);
                }
            });
        } else {
            showLongToast("Please enter a key to search");
        }
    }

    /**
     * Navigate to List activity passing {@link SearchResponse} and the search key.
     *
     * @param searchResponse
     */
    @UiThread
    protected void navigateToListActivity(SearchResponse searchResponse) {
        ListActivity_
                .intent(this)
                .mResponseData(searchResponse.getResponseData())
                .mSearchKey(mEditText.getText().toString())
                .start();
    }
}
