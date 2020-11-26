package selenium_web.Practice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @program: SeleniumType
 * @description
 * @author: chuchen
 * @create: 2020-11-17 14:03
 **/
public class CeShiRenLoginTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void makeDriver(){
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    @Test
    public  void login(){
        driver.get("https://ceshiren.com/");

        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.id("login-account-name")).sendKeys("757577405");

        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("1234qwer");

        driver.findElement(By.id("login-button")).click();
    }

    @Test
    @DisplayName("显示等待登录")
    public void waitLogIn(){
        driver.get("https://ceshiren.com/");

//        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

//        wait.until(new ExpectedCondition<WebElement>() {
//            public WebElement apply(WebDriver driver){
//                return driver.findElement(By.id("foo"));
//            }
//        });

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
        element.click();
    }

    @AfterAll
    public static void quitOut(){
        driver.quit();
    }
}
