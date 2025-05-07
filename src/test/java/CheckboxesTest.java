import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {

    /*
    Checkboxes - проверить, что первый чекбокс unchecked, отметить
    первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
    checked, сделать unhecked, проверить, что он unchecked
    Локатор: By.cssSelector("[type=checkbox]”)
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
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckbox = driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(1)"));
        WebElement secondCheckbox = driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(firstCheckbox.isSelected(), "Первый чекбокс должен быть unckecked");
        firstCheckbox.click();
        softAssert.assertTrue(firstCheckbox.isSelected(), "Первый чекбокс должен быть checked");
        softAssert.assertTrue(secondCheckbox.isSelected(), "Второй чекбокс должен быть checked");
        secondCheckbox.click();
        softAssert.assertFalse(secondCheckbox.isSelected(), "Второй чекбокс должен быть unckecked");
        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}

