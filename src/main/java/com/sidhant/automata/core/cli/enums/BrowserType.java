package com.sidhant.automata.core.cli.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum BrowserType {

    FIREFOX("Firefox"), CHROME("Chrome"), IPAD("iOS", "iPad"), IPHONE("iPhone"), ANDROID("Android"), SAFARI("Safari");

    private final String[] aliases;

    BrowserType(String... aliases) {
        this.aliases = aliases;
    }

    public static BrowserType fromString(String s) {
        for (BrowserType b : values()) {
            for (String alias : b.aliases) {
                if (s.equalsIgnoreCase(alias)) {
                    return b;
                }
            }
        }
        throw new IllegalArgumentException(s + " is not a valid BrowserType");
    }

    public String[] getAliases() {
        return aliases;
    }

    public static boolean validate(String input) {
        return Stream.of(values()).map(BrowserType::getAliases).flatMap(Arrays::stream).anyMatch(v -> v.equalsIgnoreCase(input));
    }
}
