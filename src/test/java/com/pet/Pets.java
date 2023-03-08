package com.pet;

import com.exception.PetException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.BusinessFlows;
import com.utils.CommonUtil;
import io.qameta.allure.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;



public class Pets {

    private static Logger logger = LoggerFactory.getLogger(Pets.class);

    @BeforeMethod

    public void beforeTestMethod(Method testMethod){
        logger.info("Before Testmethod: " + testMethod.getName());
    }



    @Test(priority = 0, description = "Add a pet and update the pet details")
    @Feature("Pet Information Flows")
    public void addUpdatePet(ITestContext iTestContext) throws PetException
    {
        logger.info("Test: addUpdatePet "  );
        String petName = "Dog";
        // appending random number to name and pet id to avoid duplicates
        int id =CommonUtil.generateRandNumber();
       BusinessFlows.addPet(petName, id, iTestContext);

        // update pet info - renaming petname as Doggo+petid
        String newName = "Doggo";
        BusinessFlows.updatePetInfo(newName,id);

        // get pet info to verify if update was successfull
        BusinessFlows.getPetInfo(id, newName);

    }

    @Test(priority = 1, description = "Add a purchase order, retrieve the order and delete it")
    @Feature("Purchase Order Flows")
    public void addDeletePurchaseOrder(ITestContext iTestContext) throws JsonProcessingException
    {

        logger.info("Test: addDeletePurchaseOrder "  );
        int purchaseId = CommonUtil.generateRandNumber();
        // add a purchase order
        BusinessFlows.addPurchaseOrder(purchaseId);

        // find purchase by id
        BusinessFlows.getOrderDetailsById(purchaseId);

        //delete purchase order by id
        BusinessFlows.deletePurchaseOrder(purchaseId);
        // delete functionality is not working in the api,
        // so validation test cases for deletion is not added here

    }
}
