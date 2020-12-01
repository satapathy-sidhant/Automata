package com.sidhant.automata.core.cli.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Products {

    APP1("app1"),
    APP2("app2");

    private final String fullName;

    Products(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Parses a string to a {@link Products}
     *
     * @param value input to parse
     * @return the matching product. Returns EMA if no match
     */
    public static Products parse(String value) {
        for (Products p : Products.values()) {
            if (p.toString().equalsIgnoreCase(value)) {
                return p;
            }
        }
        return APP1;
    }

    /**
     * @param value a string
     * @return whether the string is a valid product or not
     */
    public static boolean isValid(String value) {
        for (Products p : values()) {
            if (StringUtils.equalsIgnoreCase(p.toString(), value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return all the names as a list of strings
     */
    public static List<String> getNames() {
        return getStrings(Products::toString);
    }

    private static List<String> getStrings(Function<Products, String> function) {
        return Arrays.asList(values()).stream()
            .map(function)
            .collect(Collectors.toList());
    }

    /**
     * @return the nice name
     */
    public String toFormattedString() {
        return fullName;
    }
}
