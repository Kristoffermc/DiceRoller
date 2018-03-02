package com.example.kristoffer.diceroller.Model;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kristoffer.diceroller.R;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    private Context mContext;
   public ArrayList<Result> historyList = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Result> history) {
        this.mContext = context;
        this.historyList = history;
    }

    public Result getItem(int position) {return historyList.get(position); }
    public long getItemId(int position) {return 0; }

    public int getCount() {return historyList.size();}

    int getAppropiateImage(int rollValue){
        switch (rollValue) {
            case 1:
                return(R.drawable.d1);

            case 2:
                return(R.drawable.d2);

            case 3:
                return(R.drawable.d3);

            case 4:
                return(R.drawable.d4);

            case 5:
                return(R.drawable.d5);

            case 6:
                return(R.drawable.d6);

            default:
                return(R.drawable.d6);

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflaterHelper = LayoutInflater.from(mContext);
        View resultView = inflaterHelper.inflate(R.layout.result_row, parent, false);

        Result singleResultItem = getItem(position);
        TextView tvTime = (TextView) resultView.findViewById(R.id.tvTime);
        TextView tvResult = (TextView) resultView.findViewById(R.id.tvResult);
        ImageView imageDice1 = (ImageView) resultView.findViewById(R.id.imgDice1);
        ImageView imageDice2 = (ImageView) resultView.findViewById(R.id.imgDice2);

        tvResult.setText(singleResultItem.getResult1() + " / " + singleResultItem.getResult2());
        imageDice1.setImageResource(getAppropiateImage(singleResultItem.getResult1()));
        imageDice2.setImageResource(getAppropiateImage(singleResultItem.getResult2()));
        tvTime.setText("Mocked Date");

        return resultView;

    }
}
