package com.example.kristoffer.diceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kristoffer.diceroller.Model.DiceRoller;
import com.example.kristoffer.diceroller.Model.IDiceRoller;

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
        numRolls++;

        int roll1 = m_dd.RollDice();
        int roll2 = m_dd.RollDice();
        int sum = roll1+roll2;

        setDice(roll1, imgDice1);
        setDice(roll2, imgDice2);

        TextView tView = new TextView(this);
        tView.setText("Roll " + numRolls + ": " + roll1 + " + " + roll2 + " Sum: " + sum);
        listHistory.addView(tView);

        if(numRolls>16)
        {
            listHistory.removeAllViews();
            numRolls = 0;
            clickRoll();
        }
    }

    private void clickClear() {
        numRolls = 0;
        listHistory.removeAllViews();
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

