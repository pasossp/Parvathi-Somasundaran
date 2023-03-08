package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

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
