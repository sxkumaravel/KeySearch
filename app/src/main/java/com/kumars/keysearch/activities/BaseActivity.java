package com.kumars.keysearch.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.kumars.keysearch.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

/**
 * @author kumars
 */

@EActivity
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @UiThread
    protected void showLongToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    /**
     * To hide the soft keyboard manually.
     */
    protected void hideSoftKey() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * To show loading dialog
     */
    @UiThread
    protected void showLoading() {
        blockOrientation();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(BaseActivity.this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.center_spiner);
    }

    /**
     * To hide the loading dialog
     */
    @UiThread
    protected void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
        unblockOrientation();
    }

    /**
     * Unblock the screen orientation to work as default set in manifest.
     *
     * @see android.content.pm.ActivityInfo#SCREEN_ORIENTATION_SENSOR
     */
    public void unblockOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    /**
     * Call this method to block orientation.
     * This will be mostly used while in progress of power call and we show the progress dialog.
     *
     * @see android.content.pm.ActivityInfo#SCREEN_ORIENTATION_LANDSCAPE
     * @see android.content.pm.ActivityInfo#SCREEN_ORIENTATION_PORTRAIT
     * @see android.content.res.Configuration#orientation
     */
    public void blockOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
    }
}
