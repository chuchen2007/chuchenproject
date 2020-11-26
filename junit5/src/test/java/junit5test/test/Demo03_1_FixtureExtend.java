package junit5test.test;

import org.junit.jupiter.api.*;

public class Demo03_1_FixtureExtend extends Demo03_1_Fixture{
    @BeforeAll
    public static void testChildBeforeAll(){
        System.out.println("执行ChildBeforeAll方法");
    }
    @AfterAll
    public  static void testChildAfterAll(){
        System.out.println("执行ChildAfterAl方法");
    }
    @AfterEach
    public void testChildAfterEach(){
        System.out.println("执行ChildAfterEach方法");
    }
    @BeforeEach
    public void testChildBeforeEach(){
        System.out.println("执行ChildBeforeEach方法");
    }
    @Test
    public void Childtest1(){
        System.out.println("执行Childtest1方法");
    }
    @Test
    public void Childtest2(){
        System.out.println("执行Childtest2方法");
    }
}
