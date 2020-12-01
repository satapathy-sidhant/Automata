package testclasses;

import com.sidhant.automata.core.logger.MyLogger;
import com.sidhant.automata.config.CommonConfig;
import com.sidhant.automata.config.ConfigHandler;
import org.testng.annotations.Test;

public class PropertiesTest {

    CommonConfig commonConfig = null;

    @Test
    public void setup() {
        commonConfig = ConfigHandler.buildConfig("config.app1.properties");
    }

    @Test(dependsOnMethods = "setup")
    public void testServerPropertiesAreLoaded() {
        MyLogger.logTest("Server properties are loaded", "PropertiesTest");
        MyLogger.log(commonConfig.getAppServerConfig().systemTestURL(), "PropertiesTest");
        MyLogger.log(commonConfig.getAppServerConfig().UATUrl(), "PropertiesTest");
    }

    @Test(dependsOnMethods = "setup")
    public void testAppPropertiesAreLoaded() {
        MyLogger.logTest("Application properties are loaded", "PropertiesTest");
        MyLogger.logList(commonConfig.getAppConfig().users(), " App Users", "PropertiesTest");
        MyLogger.logTable(commonConfig.getAppConfig().usernames(), "App Usernames", "PropertiesTest");
    }
}
