package selenium_web.Practice;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StartSelenium {
    @Test
    public void startSelenium() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://home.testing-studio.com/");
        webDriver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
    }
//    @Test
//    void startSeleniumTwo(){
//        WebDriver webDriver  = new ChromeDriver();
//        webDriver.get("https://www.baidu.com/");
//    }

}
