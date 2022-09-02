package com.utils;

import com.constants.Payload;
import com.constants.StatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pojo.PurchaseInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class BusinessFlows

{
    private BusinessFlows()
    {}
    private static Logger logger = Logger.getLogger("CommonUtil");

    /***
     * This method adds the details of a pet in the system
     * @param petName name of the pet
     * @param id id for the pet
     * @param iTestContext to share data about the test
     */
    @Step
    public static void addPet(String petName, int id, ITestContext iTestContext)
    {
        logger.info("Test: addPet "  );
        // appending random number to name and pet id to avoid duplicates
        JSONObject petInfo = generateAddPetPayload(id, petName);
        RequestSpecification specification = new RequestSpecBuilder().setBody(petInfo.toString()).build();
        Response response =RequestManager.postRequest(Payload.add_pet_url,specification);

        logger.info("Add pet status code: "+ response.getStatusCode() );
        logger.info("Add pet response: " +  response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        String petResponse = response.getBody().asString();
        JSONObject petJsonResponse = new JSONObject(petResponse);
        Assert.assertEquals(petJsonResponse.get("id"),id);
        Assert.assertEquals(String.valueOf(petJsonResponse.get("name")),petName + id);

    }

    /***
     * This method returns the details of pets
     * @param id id of the pet
     * @param newName updated name of the pett
     */
    @Step
    public static void getPetInfo( int id, String newName)
    {
        logger.info("Test: getPetInfo " );


        RequestSpecification specification = new RequestSpecBuilder().build();
        Response response =RequestManager.getRequest(Payload.add_pet_url
                + "/" + id,specification);

        logger.info("Get pet status code: "+ response.getStatusCode() );
        logger.info("Get pet response body: " +  response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), StatusCodes.OK);
        String petResponse = response.getBody().asString();
        JSONObject petJsonResponse = new JSONObject(petResponse);
        Assert.assertEquals(petJsonResponse.get("id"),id);
        Assert.assertEquals(String.valueOf(petJsonResponse.get("name")),newName + id);

        // intentionally failing this test case to demo the test report failure
        Assert.assertEquals(response.getStatusCode(), StatusCodes.CREATED);

    }
    /***
     * This method updates the pet information
     * @param newName new name for the pet
     * @param id id of the pet
     */
    @Step
    public static void updatePetInfo(String newName, int id)
    {
        JSONObject updatedPetInfo = generateAddPetPayload(id, newName);


        RequestSpecification updatedSpecification = new RequestSpecBuilder().setBody(updatedPetInfo.toString()).build();
        Response updateResponse =RequestManager.putRequest(Payload.add_pet_url,
                updatedSpecification);

        logger.info("Update pet status code: "+ updateResponse.getStatusCode() );
        logger.info("Update pet response: " + updateResponse.getBody().asString());
        Assert.assertEquals(updateResponse.getStatusCode(), StatusCodes.OK);
        String updatedPetResponse = updateResponse.getBody().asString();
        JSONObject updatedPetJsonResponse = new JSONObject(updatedPetResponse);
        Assert.assertEquals(updatedPetJsonResponse.get("id"),id);
        Assert.assertEquals(String.valueOf(updatedPetJsonResponse.get("name")),newName + id);

    }

    /***
     * This method generates payload for add pet API
     * @param id id of the pet
     * @param petName name of the pet
     * @return returns an object with pet info
     */
    private static JSONObject generateAddPetPayload(int id,String petName)
    {
        logger.info("method: generateAddPetPayload");
        // creating request body using JSONObject construction
        JSONObject petInfo = new JSONObject();
        petInfo.put("id",id);
        petInfo.put("name",petName + id);
        JSONObject category = new JSONObject();
        category.put("id",id);
        category.put("name","category"+ id);
        petInfo.put("category",category);
        List<JSONObject> tags = new ArrayList<>();
        JSONObject tagInfo = new JSONObject();
        tagInfo.put("id",id);
        tagInfo.put("name","tag"+ id);
        tags.add(tagInfo);
        petInfo.put("tags",tags);
        petInfo.put("status","available");
        return petInfo;
    }

    /***
     * This method creates a purchase order
     * @param purchaseId order id
     */
    @Step
    public static void addPurchaseOrder(int purchaseId) throws JsonProcessingException {
        PurchaseInfo purchaseInfo = new PurchaseInfo.Builder()
                .setId(purchaseId)
                .setPetId(purchaseId)
                .setQuantity(2)
                .setShipDate("2022-08-31T17:22:30.013Z")
                .setComplete("true")
                .setStatus("approved")
                .build();

        RequestSpecification specification = new RequestSpecBuilder().setBody(purchaseInfo).build();
        Response response = RequestManager.postRequest(Payload.place_order_url ,specification);
        PurchaseInfo purchasePojo = response.as(PurchaseInfo.class);
        logger.info("Add purchase status code: "+ response.getStatusCode() );
        logger.info("Add purchase response: " + response.getBody().asString());
        Assert.assertEquals(purchasePojo.getId(),purchaseInfo.getId());
    }

    /***
     * This method fetches the order details using purchase id
     * @param purchaseId order id
     */
    @Step
    public static void getOrderDetailsById(int purchaseId)
    {
        RequestSpecification findPurchaseSpecification = new RequestSpecBuilder().build();
        Response findPurchaseResponse = RequestManager.getRequest(Payload.place_order_url
                + "/" + purchaseId,findPurchaseSpecification);
        logger.info("Find purchase status code: "+ findPurchaseResponse.getStatusCode() );
        Assert.assertEquals(findPurchaseResponse.getStatusCode(),StatusCodes.OK);

    }

    

    /***
     * This method deletes an order from the system
     * @param purchaseId order id
     */
    @Step
    public static void deletePurchaseOrder(int purchaseId)
    {
        RequestSpecification deletePurchaseSpecification = new RequestSpecBuilder().build();
        Response deletePurchaseResponse = RequestManager.deletRequest(Payload.place_order_url
                        + "/" + purchaseId,
                deletePurchaseSpecification);
        logger.info("Delete purchase status code: "+ deletePurchaseResponse.getStatusCode() );
        Assert.assertEquals(deletePurchaseResponse.getStatusCode(),StatusCodes.OK);
    }
}
