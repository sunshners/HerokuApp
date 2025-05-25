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
import java.io.File;
import java.time.Duration;

public class FileUploadTest {
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
    public void testFileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");
        File file = new File("test_upload.txt");
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys(file.getAbsolutePath());
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();
        WebElement uploadedFiles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
        Assert.assertEquals(uploadedFiles.getText(), file.getName());
        file.delete();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
