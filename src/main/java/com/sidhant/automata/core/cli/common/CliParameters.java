package com.sidhant.automata.core.cli.common;

import com.beust.jcommander.Parameter;
import com.sidhant.automata.core.cli.enums.Platforms;
import com.sidhant.automata.core.cli.enums.Products;

public class CliParameters {

    @Parameter(names = "--help", help = true)
    public boolean help;

    @Parameter(names = {"-F", "--firm"}, description = "The name of the primary firm under test", required = false)
    public String firm;

    @Parameter(names = {"-L", "--platform"}, description = "API, WEB, MOBILE", required = true, converter = RunnerCLIPlatformConverter.class)
    public Platforms platform;

    @Parameter(names = {"-P", "--product"}, description = "APP1, APP2", required = true, converter = RunnerCLIProductConverter.class)
    public Products product;

}
