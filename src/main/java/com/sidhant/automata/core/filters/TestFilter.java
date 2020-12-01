package com.sidhant.automata.core.filters;


import com.sidhant.automata.core.cli.enums.Platforms;
import com.sidhant.automata.core.cli.enums.Products;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestFilter {

    public static final String BASE_PACKAGE = "com.sidhant.automata.products.";

    private TestFilter() {
    }

    public static Set<String> getFilteredTestClassesByProduct(Set<String> tests, Products product) {
        return tests.stream().filter(s -> s.startsWith(BASE_PACKAGE + product.toFormattedString())).collect(Collectors.toCollection(HashSet::new));
    }

    public static Set<String> getFilteredTestClassesByPlatform(Set<String> tests, Platforms platform) {
        return tests.stream().filter(s -> s.startsWith(BASE_PACKAGE) && s.contains(platform.getName())).collect(Collectors.toCollection(HashSet::new));
    }
}
