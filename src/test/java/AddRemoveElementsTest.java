import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementsTest {

    /*
    1 Add/Remove Elements - добавить 2 элемента, удалить элемент,
    проверить количество элементов DELETE
    Локаторы xpath:
    a. By.xpath("//button[text()='Add Element']")
    b. By.xpath("//button[text()='Delete']")
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
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        List<WebElement> buttons = driver.findElements(By.xpath("//button[text()='Delete']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(buttons.size(),2);
        buttons.get(1).click();
        List<WebElement> buttonsAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']"));
        softAssert.assertEquals(buttonsAfterDelete.size(),1);
        buttons.get(1).click();
        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
