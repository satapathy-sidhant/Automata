package com.sidhant.automata.core.cli.common;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReflectionsHandler {

    public static final String BASE_PACKAGE = "com.sidhant.automata.products";
    public static final String EXCLUDE_PACKAGES = "(com.sidhant.automata)(\\.)(?!products.*\\.tests\\.)\\S+";
    private final Reflections reflections;

    public ReflectionsHandler() {
        reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(BASE_PACKAGE)).addScanners(new MethodAnnotationsScanner()).filterInputsBy(new FilterBuilder().includePackage(BASE_PACKAGE).exclude(EXCLUDE_PACKAGES)));
    }

    public Set<String> getAllTestClasses() {
        return reflections.getMethodsAnnotatedWith(Test.class)
            .stream()
            .map(Method::getDeclaringClass)
            .filter(this::includeTest)
            .map(Class::getCanonicalName)
            .collect(Collectors.toCollection(HashSet::new));
    }

    private boolean includeTest(Class<?> clazz) {
        return clazz.getCanonicalName().startsWith(BASE_PACKAGE);
    }

    private boolean filter(Class<?> clazz) {
        return !Pattern.compile(EXCLUDE_PACKAGES).matcher(clazz.getCanonicalName()).matches();
    }

}
