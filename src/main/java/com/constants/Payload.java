package com.constants;

import com.utils.ConfigPropertyReader;

public class Payload {

    private Payload()
    {}
    public static final String ACCEPT_HEADER ="accept";
    public static final String CONTENT_TYPE_HEADER ="Content-Type";
    public static final String APPLICATION_JSON ="application/json";

    public static String add_pet_url = ConfigPropertyReader.readConfig().
            getProperty("add_pet_url");

    public static String place_order_url = ConfigPropertyReader.readConfig()
            .getProperty("place_order_url");

    public static String base_url = ConfigPropertyReader.readConfig().getProperty("base_url");


}
