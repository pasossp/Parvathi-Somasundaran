package com.pet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.BusinessFlows;
import io.qameta.allure.Feature;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.utils.CommonUtil;
import java.lang.reflect.Method;
import java.util.logging.Logger;


public class Pets {

    private static Logger logger = Logger.getLogger("Pets");

    @BeforeMethod

    public void beforeTestMethod(Method testMethod){
        logger.info("Before Testmethod: " + testMethod.getName());
    }



    @Test(priority = 0, description = "Add a pet and update the pet details")
    @Feature("Pet Information Flows")
    public void addUpdatePet(ITestContext iTestContext)
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
    public void addDeletePurchaseOrder(ITestContext iTestContext) throws JsonProcessingException {

        logger.info("Test: addDeletePurchaseOrder "  );
        int purchaseId = CommonUtil.generateRandNumber();
        // add a purchase order
        BusinessFlows.addPurchaseOrder(purchaseId);

        // find purchase by id
        BusinessFlows.getOrderDetailsById(purchaseId);

        //delete purchase order by id
        BusinessFlows.deletePurchaseOrder(purchaseId);

    }
}
