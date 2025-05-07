import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

    /*
    Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
    Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
    он выбран
    Локатор: By.id(“dropdown”)
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
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
                Select dropdown = new Select(dropdownElement);

        List<WebElement> options = dropdown.getOptions();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(options.size(), 3, "Должно быть 3 элемента в дропдауне.");
        softAssert.assertEquals(options.get(0).getText(), "Please select", "Первый элемент должен " +
                "быть 'Please select'");
        softAssert.assertEquals(options.get(1).getText(), "Option 1", "Второй элемент должен быть" +
                        "'Option 1'");
        softAssert.assertEquals(options.get(2).getText(), "Option 2", "Третий элемент должен быть" +
                        "'Option 2'");

        dropdown.selectByIndex(1);
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1", "Первый" +
                "выбранный элемент должен быть 'Option 1'");

        dropdown.selectByIndex(2);
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2", "Первый" +
                "выбранный элемент должен быть 'Option 2'");
        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
