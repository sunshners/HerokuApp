import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {

    /*
    Typos - Проверить соответствие параграфа орфографии
    Локатор: By.tagName(“p”)
     */

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        driver.get("https://the-internet.herokuapp.com/typos");
        WebElement paragraph = driver.findElement(By.tagName("p"));
        String expectedText = "This example demonstrates a typo being introduced. It can be easily noticed.";
        String actualText = paragraph.getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualText, expectedText, "Текст параграфа не совпадает с ожидаемым.");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
