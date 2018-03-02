package com.example.kristoffer.diceroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kristoffer.diceroller.Model.CustomAdapter;
import com.example.kristoffer.diceroller.Model.Result;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    Button btnBack;
    Button btnClear;
    LinearLayout listHistory;
    CustomAdapter histAdapter;
    ListView listView;

    ArrayList<Result>  resultList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //listHistory = findViewById(R.id.listHistory);
        btnBack = findViewById(R.id.btnBack);
        btnClear = findViewById(R.id.btnClear);
        listView = findViewById(R.id.listView);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickClear();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMain();
            }
        });

        initlialize();
    }

    private ArrayList<Result> parseIntArrayToResultArray(ArrayList<Integer> intArr){
        ArrayList<Result> tempResult = new ArrayList<>();
        for (int i = 0; i < intArr.size(); i++)
        {
            if(i == 0 || i%2==0){
                tempResult.add(new Result(intArr.get(i), intArr.get(i+1)));
            }
        }
        return  tempResult;
    }

    private void initlialize() {
        Bundle extras = getIntent().getExtras();
        ArrayList<Integer> results = extras.getIntegerArrayList("results");
        resultList.addAll(parseIntArrayToResultArray(results));

        histAdapter = new CustomAdapter(this, resultList);

        TextView tView = new TextView(this);
        TextView yView = new TextView(this);


        listView.setAdapter(histAdapter);
    }

    // Change view
    private void showMain() {
        this.finish();
    }

    // Clears the history
    private void clickClear() {
        MainActivity.results.clear();
        resultList.clear();
        histAdapter.notifyDataSetChanged();
    }
}
