package com.kumars.keysearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kumars.keysearch.R;
import com.kumars.keysearch.model.ResponseData.ResultData;

import java.util.List;


/**
 * Adapter to hold and provide view for the ResultDate.
 *
 * @author kumars
 */
public class ListAdapter extends ArrayAdapter<ResultData> {

    private final Context mContext;

    public ListAdapter(Context context, List<ResultData> resultDatas) {
        super(context, R.layout.view_list, resultDatas);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResultData resultData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_list, parent, false);
        }

        TextView view = (TextView) convertView.findViewById(R.id.list_text_view);
        view.setText(resultData.getTitle());

        return convertView;
    }
}
