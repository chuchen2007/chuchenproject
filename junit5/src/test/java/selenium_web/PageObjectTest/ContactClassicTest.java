package selenium_web.PageObjectTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @program: SeleniumType
 * @description
 * @author: chuchen
 * @create: 2020-11-20 15:37
 * 将exampleselenium包中的WeChatWebTest进行了优化,并且新增企业微信部门成员
 **/
public class ContactClassicTest {
    public static WebDriver driver ;

    static void needLogin() throws InterruptedException, IOException {

        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep20s 扫码
        Thread.sleep(20000);
        //获取cookie
        Set<Cookie> cookies = driver.manage().getCookies();
        //存储cookie
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
    }

    @BeforeAll
    static void beforeAllLoginTested() throws IOException ,InterruptedException{
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        File file = new File("cookies.yaml");
        if (file.exists()) {
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            ;
            List<HashMap<String, Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(file, typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });
            //刷新页面,即可完成登录
            driver.navigate().refresh();
        }else{
            needLogin();
        }
    }

//    @Test
//    void contactAdd(){
//         driver.findElement(By.id("menu_contacts")).click();
//         driver.findElement(By.linkText("通讯录")).click();
//         driver.findElement(By.linkText("添加成员")).click();
//         driver.findElement(By.name("username")).sendKeys("玄灵子");
//         driver.findElement(By.name("acctid")).sendKeys("qwert");
//         driver.findElement(By.name("mobile")).sendKeys("15625254185");
//         driver.findElement(By.linkText("保存")).click();
//    }

    @Test
    @Disabled
    void contactAdd(){//添加成员
        click(By.linkText("通讯录"));
        click(By.linkText("添加成员"));
        sendKeys(By.name("username"),"玄灵子");
        sendKeys(By.name("acctid"),"qwert");
        sendKeys(By.name("mobile"),"15625254185");
        click(By.linkText("保存"));
    }

    @Test
    void departmentSearch(){//部门搜索
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"),"上海研发中心");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        //因为页面动态效果,导致断言失败,再次点击,利用隐形等待机制,完成加载页面等待时间,完成断言
        click(By.cssSelector(".ww_icon_AddMember"));
        content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
//        assertEquals("当前部门无任何团队成员",content.contains("当前部门无任何成员"));
        assertTrue(content.contains("无任何成员"));

    }

//    @Test
//    void departmentAdd(){//添加部门
//        click(By.linkText("添加"));
//        click(By.linkText("添加部门"));
//
//
//    }

    void click(By by){
        driver.findElement(by).click();
    }

    void sendKeys(By by ,String string){
        driver.findElement(by).sendKeys(string);
    }

}

