import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class DynamicControlsTest {

        WebDriver driver;

        @BeforeMethod
        public void setup(){
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

        @Test
        public void checkDynamicControls(){
            driver.get("https://the-internet.herokuapp.com/dynamic_controls");
            driver.findElement(By.xpath("//*[text()='Remove']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));

            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
            alert.dismiss();
            alert.sendKeys("");

            Actions actions = new Actions(driver);


            driver.findElement(By.xpath("//input[@type = 'file]"))
                            .sendKeys("");
            actions
                    .moveToElement(driver.findElement(By.id("menu")))
                    .pause(Duration.ofSeconds(10))
                    .moveToElement(driver.findElement(By.id("element")))
                    .pause(Duration.ofSeconds(10))
                    .contextClick()
                    .click()
                    .build()
                    .perform();



        }


    @Test
    public void checkInput(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        Boolean fieldIsDisabled = driver.findElement(By.cssSelector("[type='text']")).isEnabled();
        assertEquals(fieldIsDisabled, false, "Поле раздизейблено");
        driver.findElement(By.xpath("//*[text()='Enable']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Boolean fieldIsEnabled = driver.findElement(By.cssSelector("[type='text']")).isEnabled();
        assertEquals(fieldIsEnabled, true, "Поле задизейблено");
    }


        @AfterMethod(alwaysRun = true)
        public void tearDown(){
            driver.quit();
        }
    }

