import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    /*
    Inputs - Проверить на возможность ввести различные цифровые и
    нецифровые значения, используя Keys.ARROW_UP И
    Keys.ARROW_DOWN
    Локатор: By.tagName(“input”)
     */

    WebDriver driver;
    WebElement inputField;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        driver.get("https://the-internet.herokuapp.com/inputs");
        inputField = driver.findElement(By.tagName("input"));
        inputField.clear();
        inputField.sendKeys("10");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(inputField.getAttribute("value"), "10", "Поле должно" +
                "содержать '10'");

        inputField.sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(inputField.getAttribute("value"), "11", "После нажатия" +
                "стрелки вверх значение должно быть '11'");

        inputField.sendKeys(Keys.ARROW_DOWN); // Уменьшение значения
        softAssert.assertEquals(inputField.getAttribute("value"), "10", "После нажатия" +
                "стрелки вниз значение должно быть '10'");

        inputField.clear();
        inputField.sendKeys("abc");
        softAssert.assertEquals(inputField.getAttribute("value"), "abc", "Поле должно" +
                "содержать 'abc'");

        inputField.sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(inputField.getAttribute("value"), "abc", "Значение не должно" +
                "измениться при нажатии стрелки вверх на текстовом вводе");

        inputField.sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(inputField.getAttribute("value"), "abc", "Значение не должно" +
                "измениться при нажатии стрелки вниз на текстовом вводе");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
