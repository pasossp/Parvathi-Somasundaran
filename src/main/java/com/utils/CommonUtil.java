package com.utils;

import org.apache.log4j.Logger;
import java.util.Random;

public class CommonUtil {


    private static Logger logger = Logger.getLogger("CommonUtil");

    /***
     * This method generates a random number below 99999
     * This will be used to append to names and id to avoid duplication
     * @return returns random number
     */
    public static int generateRandNumber()
    {
        logger.info("method: generateRandNumber");
        Random rand = new Random();
        return rand.nextInt(99999);
    }
}
