package framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @program: SeleniumType
 * @description
 * @author: chuchen
 * @create: 2020-11-27 16:17
 * 参数化@ParameterizedTest @MethodSource
 * @ValueSource
 **/
public class ParamsTest {
    //todo:数据驱动测试步骤
    //todo:数据驱动测试数据
    //todo:断言

    @ParameterizedTest
    @MethodSource("stringProvider")
        //参数化方式一
    void testWithExplicitLocalMethodSource(String argument){

        assertNotNull(argument);
    }

    static Stream<String> stringProvider(){
        return Stream.of("倚楼听风雨","淡看江湖路");
    }

    @ParameterizedTest
    @MethodSource
        //参数化方式二
    void ParamsTest(String argument){
        assertNotNull(argument);
    }

    static Stream<String> ParamsTest(){
        return Stream.of("寒心恨雪","锋霜影雪");
    }

    @Test
        //原始searchTest
    void searchTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys("漫天风雪");
    }

    @Test
    @ValueSource(strings  = {"飞燕反","落英回"})
        //参数化方式一searchValueSourceTest
    void searchValueSourceTest(String keyWords){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys(keyWords);
    }

    @ParameterizedTest
    @MethodSource
        //参数化方式二searchParameterizedTest
    void searchParameterizedTest(String keyWords){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys(keyWords);
    }

    static Stream<String> searchParameterizedTest(){
        return Stream.of("飞燕返","落英回");
    }
    @ParameterizedTest
    @MethodSource
        //数据举动测试步骤searchStepsTest
    void searchStepsTest(TestCase testCase){
        System.out.println(testCase);
        testCase.run();
    }

    static Stream<TestCase> searchStepsTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testCase = mapper.readValue (
                ParamsTest.class.getResourceAsStream("/framework/search.yaml"),
                TestCase.class);
        return Stream.of(testCase);
    }

//    @AfterAll
//    void exitTest(){
//        WebDriver driver = new ChromeDriver();
//        driver.quit();
//    }


}