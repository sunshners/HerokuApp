package hm_2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class DynamicControlTest {
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
    public void checkDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        removeButton.click();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(message.getText(), "It's gone!");
        Assert.assertTrue(driver.findElements(By.id("checkbox")).isEmpty());
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertFalse(input.isEnabled());
        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        enableButton.click();
        WebElement enableMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(enableMessage.getText(), "It's enabled!");
        Assert.assertTrue(input.isEnabled());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
