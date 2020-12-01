package com.sidhant.automata.core.cli.common;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.sidhant.automata.core.cli.enums.Products;

public class RunnerCLIProductConverter implements IStringConverter<Products> {

    @Override
    public Products convert(String value) {
        Products convertedValue = Products.parse(value);

        if (convertedValue == null) {
            throw new ParameterException("Value " + value + "can not be converted to BugSprayProduct.");
        }
        return convertedValue;
    }
}
