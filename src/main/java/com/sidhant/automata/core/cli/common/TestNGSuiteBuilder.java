package com.sidhant.automata.core.cli.common;

import com.sidhant.automata.core.cli.config.FmwkConfig;
import com.sidhant.automata.core.logger.MyLogger;
import org.apache.commons.lang3.StringUtils;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.time.LocalDate;
import java.util.*;

public class TestNGSuiteBuilder {

    List<XmlTest> xmlTests = new ArrayList<>();

    private XmlSuite createSuite() {
        XmlSuite newSuite = new XmlSuite();
        newSuite.setJUnit(false);
        newSuite.setThreadCount(1);
        newSuite.setName("This suite file was created at : " + LocalDate.now());
        newSuite.setParallel(XmlSuite.ParallelMode.TESTS);
        newSuite.setVerbose(1);
        newSuite.setPreserveOrder(true);
        Map<String, String> params = new HashMap<>();
        params.put("session-id", Long.toString(System.currentTimeMillis()));
        newSuite.setParameters(params);
        return newSuite;
    }

    public List<XmlSuite> build(Set<String> tests) {
        List<XmlSuite> suites = new ArrayList<>();
        tests.stream()
            .map(this::toXmlTests)
            .forEach(xmlTests::add);

        Collections.shuffle(xmlTests);
        addSuite(suites, xmlTests);
        return suites;
    }

    private XmlTest toXmlTests(String className) {
        XmlTest xmlTest = new XmlTest();
        xmlTest.setJUnit(false);
        xmlTest.setClasses(Collections.singletonList(new XmlClass(className)));
        String name = StringUtils.substringAfterLast(className, ".");
        xmlTest.setName(name);
        xmlTest.addParameter("firm", FmwkConfig.getInstance().getFirm());
        return xmlTest;

    }

    private void addSuite(List<XmlSuite> xmlSuites, List<XmlTest> tests) {
        XmlSuite suite = createSuite();
        tests.forEach(test -> {
            test.setSuite(suite);
            suite.addTest(test);
        });
        xmlSuites.add(suite);
        MyLogger.log(suite.toXml(), "SuiteBuilder");
    }

}
