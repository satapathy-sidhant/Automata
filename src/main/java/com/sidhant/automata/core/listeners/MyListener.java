package com.sidhant.automata.core.listeners;

import com.sidhant.automata.core.annotations.AnnotationHandler;
import com.sidhant.automata.core.annotations.Description;
import com.sidhant.automata.core.logger.MyLogger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.LinkedHashMap;

public class MyListener implements ITestListener {

    LinkedHashMap<String, String> testResults = new LinkedHashMap<>();
    String testName = "";

    @Override public void onTestStart(ITestResult result) {
        String name = Description.class.getSimpleName();
        if (AnnotationHandler.hasAnnotation(result, Description.class, false)) {
            Description annotation = AnnotationHandler.getAnnotation(result, Description.class);
            MyLogger.logTest(annotation.value(), "T" + Thread.currentThread().getId() + " " + result.getMethod().getMethodName());
            testName = annotation.value();
        } else {
            MyLogger.log("The method " + result.getMethod().getMethodName() + " was not annotated with @" + name , "Test Start");
            testName = result.getMethod().getMethodName();
        }

    }

    @Override public void onTestSuccess(ITestResult result) {
        testResults.put(testName, "passed √");
    }

    @Override public void onTestFailure(ITestResult result) {
        MyLogger.log(testName + ":::::FAILED with exception : " + result.getThrowable().getMessage(), "after test");
        testResults.put(testName, "failed X");
    }

    @Override public void onTestSkipped(ITestResult result) {

    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override public void onStart(ITestContext context) {

    }

    @Override public void onFinish(ITestContext context) {
        MyLogger.logTable(testResults, "Results for : " + context.getAllTestMethods()[0].getRealClass().getSimpleName(), "Result Logger");
    }
}
