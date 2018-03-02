package com.example.kristoffer.diceroller.Model;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kristoffer.diceroller.R;


class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, String[] results ) {
        super(context, R.layout.result_row ,results);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflaterHelper = LayoutInflater.from(getContext());
        View resultView = inflaterHelper.inflate(R.layout.result_row, parent, false);

        String singleResultItem = getItem(position);
        TextView tvResult = (TextView) resultView.findViewById(R.id.resultText);
    }
}
