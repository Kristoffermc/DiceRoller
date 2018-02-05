package com.example.kristoffer.diceroller.Model;

import java.util.Random;

/**
 * Created by kristoffer on 05/02/2018.
 */

public class DiceRoller implements IDiceRoller {

    Random rand;

    public DiceRoller() {
        rand = new Random();
    }

    @Override
    public int RollDice() {
        int  n = rand.nextInt(6) + 1;
        return n;
    }
}
