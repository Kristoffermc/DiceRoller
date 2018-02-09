package com.example.kristoffer.diceroller;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.kristoffer.diceroller.Model.DiceRoller;
import com.example.kristoffer.diceroller.Model.IDiceRoller;
import com.example.kristoffer.diceroller.Model.Result;


public class MainActivity extends AppCompatActivity {

    Button btnRoll;
    Button btnClear;

    ImageView imgDice1;
    ImageView imgDice2;

    LinearLayout listHistory;

    IDiceRoller m_dd;

    int numRolls = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRoll = findViewById(R.id.btnRollDice);
        btnClear = findViewById(R.id.btnClear);
        imgDice1 = findViewById(R.id.imgDice1);
        imgDice2 = findViewById(R.id.imgDice2);
        listHistory = findViewById(R.id.listHistory);

        m_dd = new DiceRoller();
        btnClear.setEnabled(false);


        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRoll();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickClear();
            }
        });

    }

    private void clickRoll() {
        if(!btnClear.isEnabled()) {
            btnClear.setEnabled(true);
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
                if (count == 5) // 5 iterations of 0.1 second per iteration
                {
                    roll(rollA,rollB);
                    btnRoll.setEnabled(true);
                    return;
                }
                handler.postDelayed(this, 100L);  // 0.1 second delay
            }
        };
        runnable.run();
    }

    private void roll(int one, int two) {
        numRolls++;

        Result result = new Result();
        result.setResult1(one);
        result.setResult2(two);

        // Calculates the rolls
        int roll1 = result.getResult1();
        int roll2 = result.getResult2();
        int sum = roll1+roll2;

        // Adds a TextView to the LinearLayout of our history
        TextView tView = new TextView(this);
        tView.setText("Roll " + numRolls + ": " + roll1 + " + " + roll2 + " Sum: " + sum);
        listHistory.addView(tView);

        // Clears the history if it exceeds the screen (For my phone - Will be refactored later to fit all screens)
        if(numRolls>16)
        {
            clickClear();
            clickRoll();
        }

        // Vibrates the device
        Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.vibrate(500);
    }

    // Clears the history
    private void clickClear() {
        numRolls = 0;
        listHistory.removeAllViews();

        imgDice1.setImageResource(R.drawable.d6);
        imgDice2.setImageResource(R.drawable.d6);
    }

    private void setDice(int number, ImageView imageView) {
        switch (number)
        {
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

}




