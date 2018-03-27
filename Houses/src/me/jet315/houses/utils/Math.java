package me.jet315.houses.utils;

import me.jet315.houses.Core;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Jet on 07/02/2018.
 */
public class Math {

    /**
     *
     * @param levelHouse Current level of house
     * @param getHousePriceAlgorithm The house price algorithm that is being used
     * @return the house price
     */
    public static int calculateHousePrice(int levelHouse,String getHousePriceAlgorithm){
        //Get the calculation as a string
        String nextHousePrice = getHousePriceAlgorithm.replace("{CURRENTHOUSELEVEL}",String.valueOf(levelHouse));

        //JS
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            return ((Number) engine.eval(nextHousePrice)).intValue();
        } catch (ScriptException e) {
            e.printStackTrace();
            System.out.println("AN ERROR HAS OCCURRED WHILE PROCESSING YOUR HOUSE PRICE ALGORITHM: Ensure that " + nextHousePrice + " is a valid input!");
            return -1;
        }
    }

    /**
     *
     * @param millisecondsOfExpiry - The expiry time, measured from Epoch, of when the house expires
     * @return Days,Minutes,Hours,Seconds in an array of integers
     */
    public static Integer[] calculateTimeLeft(long millisecondsOfExpiry){
        Integer[] list = new Integer[4];
        long milliseconds = millisecondsOfExpiry - System.currentTimeMillis();
        list[3] = (int) (milliseconds / 1000) % 60 ;
        list[2] = (int) ((milliseconds / (1000*60)) % 60);
        list[1]   = (int) ((milliseconds / (1000*60*60)) % 24);
        list[0] = (int) ((milliseconds / (1000*60*60*24)));
        return list;
    }



}
