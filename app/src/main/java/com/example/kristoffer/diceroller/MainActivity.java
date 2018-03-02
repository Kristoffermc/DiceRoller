package com.example.kristoffer.diceroller;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kristoffer.diceroller.Model.DiceRoller;
import com.example.kristoffer.diceroller.Model.IDiceRoller;
import com.example.kristoffer.diceroller.Model.Result;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String tag = "XYZ";
    Button btnRoll;
    Button btnHistory;
    ImageView imgDice1;
    ImageView imgDice2;
    LinearLayout listHistory;
    IDiceRoller m_dd;

    public static ArrayList<Integer> results = new ArrayList<>();


    public int numRolls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRoll = findViewById(R.id.btnRollDice);
        btnHistory = findViewById(R.id.btnHistory);
        imgDice1 = findViewById(R.id.imgDice1);
        imgDice2 = findViewById(R.id.imgDice2);
        //listHistory = findViewById(R.id.listHistory);

        if (results == null)
            if (savedInstanceState != null)
            {
                Log.d(tag, "restored from bundle");
                results = (ArrayList<Integer>) savedInstanceState.getSerializable("results");
            }
            else {
                Log.d(tag, "Created");
                results = new ArrayList<>();
            }

        btnHistory.setEnabled(results.size() > 0);

        m_dd = new DiceRoller();



        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRoll();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistory();
            }
        });
    }

    // Change view
    private void showHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putIntegerArrayListExtra("results", results);
        Log.d(tag, "send to history: " + results);
        startActivity(intent);
    }

    private void clickRoll() {
        if (!btnHistory.isEnabled()) {
            btnHistory.setEnabled(true);
        }
        btnRoll.setEnabled(false);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            // Animation
            @Override
            public void run() {
                int rollA = m_dd.RollDice();
                int rollB = m_dd.RollDice();

                setDice(rollA, imgDice1);
                setDice(rollB, imgDice2);
                count++;
                if (count == 5) // 5 roll animation
                {
                    roll(rollA, rollB);
                    btnRoll.setEnabled(true);
                    return; // Breaks the runnable
                }
                handler.postDelayed(this, 75L); // Each animation picture lasts 0.075 seconds
            }
        };
        runnable.run(); // Starts the runnable
    }

    private void roll(int one, int two) {
        numRolls++;

        Result result = new Result(one, two);

        // Calculates the rolls
        int roll1 = result.getResult1();
        int roll2 = result.getResult2();
        int sum = roll1 + roll2;

        // Adds a TextView to the LinearLayout of the history
        TextView tView = new TextView(this);
        tView.setText("Roll " + numRolls + ": " + roll1 + " + " + roll2 + " Sum: " + sum);
        results.add(result.getResult1());
        results.add(result.getResult2());

        // Vibrates the device
        Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.vibrate(250);
    }

    private void setDice(int number, ImageView imageView) {
        switch (number) {
            case 1:
                imageView.setImageResource(R.drawable.d1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.d2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.d3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.d4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.d5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.d6);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);

       savedInstanceState.putIntegerArrayList("results", results );
       Log.d(tag, "onSave..." + results);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(tag, "onRestore called");
        if (savedInstanceState != null)
        {
            Log.d(tag, "restored from bundle");
            results = (ArrayList<Integer>) savedInstanceState.getSerializable("results");
        }
        else {
            Log.d(tag, "Created");
            results = new ArrayList<>();
        }

        btnHistory.setEnabled(results.size() > 0);
    }
}




