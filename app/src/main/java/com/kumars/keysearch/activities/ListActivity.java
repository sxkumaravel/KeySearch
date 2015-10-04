package com.kumars.keysearch.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kumars.keysearch.R;
import com.kumars.keysearch.adapter.ListAdapter;
import com.kumars.keysearch.model.ResponseData;
import com.kumars.keysearch.model.ResponseData.ResultData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Activity to show the list of search results.
 *
 * @author kumars
 */
@EActivity(R.layout.activity_list)
public class ListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewById(R.id.list_view)
    ListView mListView;

    @Extra
    ResponseData mResponseData;

    @Extra
    String mSearchKey;

    @AfterViews
    void afterViews() {
        if (mResponseData != null) {
            ListAdapter listAdapter = new ListAdapter(this, mResponseData.getResults());
            mListView.setAdapter(listAdapter);
            mListView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResultData resultData = (ResultData) parent.getItemAtPosition(position);

        // navigate to DetailList activity with an extra ResultData and the search key.
        DetailActivity_
                .intent(this)
                .mResultData(resultData)
                .mSearchKey(this.mSearchKey)
                .start();
    }
}
