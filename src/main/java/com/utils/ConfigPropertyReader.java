package com.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;


public class ConfigPropertyReader {

    static InputStream inputStream;
    public static Properties prop = new Properties();
    private static Logger logger = Logger.getLogger("ConfigPropertyReader");

    /***
     * This method reads config.property file and gets the property values
     * @return
     */
    public static Properties readConfig() {
        logger.info("method: readConfig");
        try {
            String propFileName = "config.properties";
            inputStream = ConfigPropertyReader.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
                return prop;
            } else {

                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


        } catch (
                IOException ex) {
            logger.error("IOException",ex);

        }

        return null;
    }
}
