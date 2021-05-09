package com.yatsynaekateryna.lessons.lesson14;

import java.math.BigInteger;
import java.util.Random;

public class GuessNumberGame {

    private BigInteger guessMe;

    public GuessNumberGame() {
        //generates random number in semi-interval [0..2^8192)
        //to compare with int [0..2^31) or long [0..2^63)
        guessMe = new BigInteger(8192, new Random());
    }

    @Override
    public String toString() {
        return ""+ guessMe;
    }

    public int guess(BigInteger val) {
        int compare = guessMe.compareTo(val);
        if (compare == 0) {
            System.out.printf("Guessed! = %s\n", val.toString());
        } else if (compare < 0) {
            System.out.println("Less!");
        } else {
            System.out.println("More!");
        }
        return compare;
    }

}
