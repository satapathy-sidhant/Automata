package com.sidhant.automata.core.cli.common;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.sidhant.automata.core.cli.enums.BrowserType;
import org.apache.commons.lang3.StringUtils;

public class RunnerCliBrowserConverter implements IStringConverter<BrowserType> {

    @Override public BrowserType convert(String value) {
        if (value.contains("${") || StringUtils.isEmpty(value)) {
            return null;
        }
        BrowserType convertedValue;
        try {
            convertedValue = BrowserType.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new ParameterException("Value " + value + "can not be converted to BrowserType.");
        }
        if (!BrowserType.FIREFOX.equals(convertedValue) || !BrowserType.CHROME.equals(convertedValue)) {
            throw new ParameterException("Browser parameter can only be used with Firefox or Chrome");
        }
        return convertedValue;
    }
}
