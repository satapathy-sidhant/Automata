package testclasses;

import com.sidhant.automata.core.cli.enums.Platforms;
import com.sidhant.automata.core.cli.enums.Products;
import com.sidhant.automata.core.filters.TestFilter;
import com.sidhant.automata.core.logger.MyLogger;
import com.sidhant.automata.core.runner.CliRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FilteringTests {

    public static final String SUB_STRING = ".tests.";

    @Test
    public void testTestCaseFiltering() {
        ArrayList<String> tests = new ArrayList<>(CliRunner.getAllTests());
        assert tests.stream().allMatch(klass -> klass.contains(SUB_STRING));
        MyLogger.logList(tests, "Filtered Tests", "FilteringTests");
    }

    @Test
    public void testProductWiseFiltering() {
        MyLogger.logList(new ArrayList<>(TestFilter.getFilteredTestClassesByProduct(CliRunner.getAllTests(), Products.APP2)), "Product Wise Test Classes", "FilteringTests");
    }

    @Test
    public void testPlatformWiseFiltering() {
        MyLogger.logList(new ArrayList<>(TestFilter.getFilteredTestClassesByPlatform(CliRunner.getAllTests(), Platforms.API)), "Platform Wise Test Classes", "FilteringTests");
    }
}
