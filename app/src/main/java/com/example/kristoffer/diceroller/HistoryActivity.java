package com.example.kristoffer.diceroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    Button btnBack;
    Button btnClear;
    LinearLayout listHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listHistory = findViewById(R.id.listHistory);
        btnBack = findViewById(R.id.btnBack);
        btnClear = findViewById(R.id.btnClear);
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

    private void initlialize() {
        Bundle extras = getIntent().getExtras();
        List<Integer> results = extras.getIntegerArrayList("results");
        TextView tView = new TextView(this);
        TextView yView = new TextView(this);

        tView.setText("First roll: " + results.get(0) + " + " + results.get(1));
        listHistory.addView(tView);
        yView.setText("All rolls: " + results.toString());
        listHistory.addView(yView);
    }

    // Change view
    private void showMain() {
        finish();
    }

    // Clears the history
    private void clickClear() {
        listHistory.removeAllViews();
    }
}
