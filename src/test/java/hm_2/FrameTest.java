package hm_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FrameTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void testFrames() {
        driver.get("https://the-internet.herokuapp.com/frames");
        WebElement iframeLink = driver.findElement(By.linkText("iFrame"));
        iframeLink.click();
        driver.switchTo().frame("mce_0_ifr");
        WebElement paragraph = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        Assert.assertEquals(paragraph.getText(), "Your content goes here.");
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
