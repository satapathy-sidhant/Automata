package com.sidhant.automata.core.annotations;

import org.testng.ITestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationHandler {

    private AnnotationHandler(){}

    public static boolean hasAnnotation(ITestResult testInfo, Class<? extends Annotation> annotationClass, boolean shouldLogMissing) {
        boolean hasAnnotation = false;
        String name = annotationClass.getSimpleName();

        try {
            hasAnnotation = testInfo.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(annotationClass);
        } catch (NullPointerException e) {
            logNullTestResult(name);
        }
        if (shouldLogMissing) {
            logMissingAnnotation(name);
        }
        return hasAnnotation;
    }

    public static <T extends Annotation> T getAnnotation(ITestResult testInfo, Class<T> annotationClass) {
        return getAnnotation(testInfo.getMethod().getConstructorOrMethod().getMethod(), annotationClass);
    }

    public static <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass) {
        return method.getAnnotation(annotationClass);
    }

    private static void logNullTestResult(String name) {
        System.out.println("Got a Null Pointer with: [@" + name + "] b/c the test result or method was null.");
    }

    private static void logMissingAnnotation(String annotationName) {
        System.err.println("The Test was not annotated with @" + annotationName);
    }
}
