package com.sidhant.automata.core.cli.enums;

import java.util.Arrays;

public enum Platforms {

    WEB("web"), MOBILE("mobile"), API("api");

    private String name;

    Platforms(String value) {
        this.name = value;
    }

    public static Platforms fromPlatform(String value) {
        return Arrays.stream(Platforms.values()).filter(s -> s.name.equalsIgnoreCase(value)).findFirst().orElse(Platforms.WEB);
    }

    public String getName() {
        return name;
    }
}
