package com.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static Logger logger = LoggerFactory.getLogger(TestListener.class);
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("Running onStart method " + iTestContext.getName());


    }

    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("Running onFinish method " + iTestContext.getName());

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Running onTestStart method " +  getTestMethodName(iTestResult) + " start");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Running onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("Running onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("Running onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
    }


}