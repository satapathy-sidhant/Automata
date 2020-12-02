package testclasses;

import com.sidhant.automata.core.annotations.AnnotationHandler;
import com.sidhant.automata.core.annotations.Description;
import com.sidhant.automata.core.logger.MyLogger;
import com.sidhant.automata.core.runner.CliRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AnnotationHandlerTests {

    @Test
    public void testFetchingAnnotations() {
        List<String> allTests = new ArrayList<>(CliRunner.getAllTests());
        allTests.stream()
            .map(m -> {
                try {
                    return Class.forName(m);
                } catch (ClassNotFoundException e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .peek(m -> MyLogger.log("\tGetting test descriptions for : " + m.getSimpleName(), "class stream"))
            .map(Class::getMethods)
            .flatMap(Arrays::stream)
            .filter(m -> m.isAnnotationPresent(Description.class))
            .forEach(m -> MyLogger.log("\t" + AnnotationHandler.getAnnotation(m, Description.class).value(), "Annotation Test"));

    }
}
