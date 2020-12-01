package com.sidhant.automata.core.cli.common;

import com.beust.jcommander.IStringConverter;
import com.sidhant.automata.core.cli.enums.Platforms;

public class RunnerCLIPlatformConverter implements IStringConverter<Platforms> {

    @Override public Platforms convert(String value) {
       return Platforms.fromPlatform(value);
    }
}
