package junit5test.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Demo03_1_Fixture {
    @BeforeAll
    public static void testBeforeAll(){
        System.out.println("执行BeforeAll方法");
    }
    @AfterAll
    public  static void testAfterAll(){
        System.out.println("执行AfterAl方法");
    }
    @AfterEach
    public void testAfterEach(){
        System.out.println("执行AfterEach方法");
    }
    @BeforeEach
    public void testBeforeEach(){
        System.out.println("执行BeforeEach方法");
    }
    @Test
    public void test1(){
        System.out.println("执行test1方法");
    }
    @Test
    public void test2(){
        System.out.println("执行test2方法");
    }

}