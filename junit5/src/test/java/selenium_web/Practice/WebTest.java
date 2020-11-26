package selenium_web.Practice;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebTest {
    public WebDriver driver;

    @Test
    public void searchTest(){
        driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        driver.get("https://ceshiren.com/");
        driver.findElement(By.cssSelector(".d-icon-user")).click();
        //id 定位
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys("selenium_web");

        //CSS定位
        driver.findElement(By.cssSelector("#search-button")).click();
        driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium_web");
          driver.findElement(By.cssSelector(".d-icon-search")).click();
          driver.findElement(By.cssSelector(".search-input .search-term")).sendKeys("selenium_web");
        //Xpath定位
          driver.findElement(By.xpath("//*[@title=\"搜索主题、帖子、用户或分类\"]")).click();
          driver.findElement(By.xpath("//*[@placeholder=\"搜索主题、帖子、用户或分类\"]")).sendKeys("selenium_web");
    }
}
