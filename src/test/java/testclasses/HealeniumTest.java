package testclasses;

import com.epam.healenium.SelfHealingDriver;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HealeniumTest {

    public static final String TEXT_BOX = "//input[@id='textbox']";
    public static final String BUTTON = "//button[.='save1']";

    @Test
    public void test() throws IOException {
        WebDriver delegate = new ChromeDriver();
        Config config = ConfigFactory.load("healenium.properties");
        SelfHealingDriver driver = SelfHealingDriver.create(delegate, config);
        URL[] urls = FileUtils.toURLs(new File[]{new File("./src/test/resources/TestFile.html")});
        driver.get(urls[0].toString());
        enterText(driver, TEXT_BOX);
        click(driver, BUTTON);
        screenshot(delegate);
        sleep();
        driver.quit();

    }

    private static void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void enterText(SelfHealingDriver driver, String element) {
        driver.findElement(By.xpath(element)).sendKeys("We are testing Healenium");
    }

    public static void click(SelfHealingDriver driver, String element) {
        driver.findElement(By.xpath(element)).click();
    }

    public void screenshot(WebDriver driver) {
        File ss = new File(System.getProperty("user.dir") + "/screenshots/ss.png");
        File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f, ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
