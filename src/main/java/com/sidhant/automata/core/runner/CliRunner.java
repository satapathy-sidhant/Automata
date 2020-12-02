package com.sidhant.automata.core.runner;

import com.beust.jcommander.JCommander;
import com.sidhant.automata.config.ConfigHandler;
import com.sidhant.automata.core.cli.common.CliParameters;
import com.sidhant.automata.core.cli.common.ReflectionsHandler;
import com.sidhant.automata.core.cli.common.TestNGSuiteBuilder;
import com.sidhant.automata.core.cli.config.FmwkConfig;
import com.sidhant.automata.core.filters.TestFilter;
import com.sidhant.automata.core.logger.MyLogger;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CliRunner {

    public static void main(String[] args) {
        CliParameters params = new CliParameters();
        readParams(params, args);
        if (!params.help) {
            MyLogger.logList(Arrays.asList(params.firm, params.product, params.platform), "Cli Parameters", "MAIN");
            FmwkConfig.getInstance().createTheConfig(params, ConfigHandler.buildConfig("config.app1.properties"));
            List<XmlSuite> suiteFiles = new TestNGSuiteBuilder().build(getFilteredTests(getAllTests(), params));
            TestNG tng = new TestNG();
            tng.setUseDefaultListeners(false);
            tng.setVerbose(1);
            tng.setXmlSuites(suiteFiles);
            tng.run();
        }
    }

    public static void readParams(CliParameters params, String... args) {
        JCommander jCommander = new JCommander(params);
        jCommander.parse(args);
        if (params.help) {
            jCommander.usage();
        }
    }

    public static Set<String> getAllTests() {
        ReflectionsHandler reflectionsHandler = new ReflectionsHandler();
        return new HashSet<>(reflectionsHandler.getAllTestClasses());
    }

    public static Set<String> getFilteredTests(Set<String> testClasses, CliParameters params) {
        Set<String> classes = new HashSet<>(testClasses);
        classes = TestFilter.getFilteredTestClassesByProduct(classes, params.product);
        classes = TestFilter.getFilteredTestClassesByPlatform(classes, params.platform);
        return classes;
    }

}
