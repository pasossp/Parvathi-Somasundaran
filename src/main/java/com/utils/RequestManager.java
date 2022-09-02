package com.utils;
import com.constants.Payload;
import org.testng.ITestContext;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Set;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class RequestManager {

    private RequestManager(){};

    private static Logger logger = Logger.getLogger("RequestManager");

    /***
     * Used to send HTTP Get request to the url
     * @param url url for the GET Request
     * @param specification  can be used to set body or params
     * @return response of the api
     */
    public static Response getRequest(String url, RequestSpecification specification)
    {
        logger.info("GET Request with url: " + Payload.base_url +url );
        return given()
                .spec(specification)
                .header(Payload.ACCEPT_HEADER,Payload.APPLICATION_JSON)
                .header(Payload.CONTENT_TYPE_HEADER,Payload.APPLICATION_JSON)
                .when()
                .get(Payload.base_url + url);
    }

    /***
     * Used to send HTTP post request to the url
     * @param url url for the POST Request
     * @param specification  can be used to set body or params
     * @return response of the api
     */
    public static Response postRequest(String url, RequestSpecification specification)
    {
        logger.info("POST Request with url: " + Payload.base_url +url );
        return given()
                .spec(specification)
                .header(Payload.ACCEPT_HEADER,Payload.APPLICATION_JSON)
                .header(Payload.CONTENT_TYPE_HEADER,Payload.APPLICATION_JSON)
                .when()
                .post(Payload.base_url + url);
    }

    /***
     * Used to send HTTP put request to the url
     * @param url url for the PUT Request
     * @param specification  can be used to set body or params
     * @return response of the api
     */
    public static Response putRequest(String url, RequestSpecification specification)
    {
        logger.info("PUT Request with url: " + Payload.base_url + url );
        return given()
                .spec(specification)
                .header(Payload.ACCEPT_HEADER,Payload.APPLICATION_JSON)
                .header(Payload.CONTENT_TYPE_HEADER,Payload.APPLICATION_JSON)
                .when()
                .put(Payload.base_url + url);
    }

    /***
     * Used to send HTTP DELETE request to the url
     * @param url url for the DELETE Request
     * @param specification  can be used to set body or params
     * @return response of the api
     */
    public static Response deletRequest(String url, RequestSpecification specification)
    {
        logger.info("DELETE Request with url: " + Payload.base_url + url );
        return given()
                .spec(specification)
                .header(Payload.ACCEPT_HEADER,Payload.APPLICATION_JSON)
                .header(Payload.CONTENT_TYPE_HEADER,Payload.APPLICATION_JSON)
                .when()
                .delete(Payload.base_url + url);
    }

}
