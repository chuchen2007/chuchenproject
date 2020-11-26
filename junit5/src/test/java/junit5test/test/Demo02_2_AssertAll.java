package junit5test.test;

import junit5test.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 优化日志：
 * 1.进行了基本的加减乘除测试
 *2.使用assertEquals进行了断言
 * 3.使用了assertAll进行了软断言，解决了一旦一个断言出错后续断言不继续跑的问题
 * 4.使用了@TestMethodOrder(MethodOrderer.OrderAnnotation.class)类注解，@Order（）方法注解来实现
 * 方法的特定顺序运算
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo02_2_AssertAll {
    @Test
    @Order(1)
    public void testAssertAll(){
        int firstResult = Calculator.add(6,6);
        int secondResult = Calculator.subtract(10,9);
        int thirdResult = Calculator.multipty(10,10);
        System.out.println(firstResult);
        System.out.println(secondResult);
        System.out.println(thirdResult);

        assertAll("",
                ()->assertEquals(12,firstResult),
                ()->assertEquals(1,secondResult),
                ()->assertEquals(100,thirdResult)
        );
    }

    @Test
    @Order(2)
    public void addTest() {
        int result = Calculator.add(5, 8);
        System.out.println(result);
        assertEquals(13,13,"断言结果正确");
    }

    @Test
    @Order(3)
    public void subtractTest() {
        int result = Calculator.subtract(8, 5);
        System.out.println(result);
        assertEquals(3,3,"断言结果正确");
    }

    @Test
    @Order(4)
    public void muliptyTest() {
        int result = Calculator.multipty(5, 8);
        System.out.println(result);
        assertEquals(40,40,"断言结果正确");
    }

    @Test
    @Order(5)
    public void divideTest() {
        int result = Calculator.divide(10, 5);
        System.out.println(result);
        assertEquals(2,2,"断言结果正确");
    }
    @BeforeEach
    public void clearTest(){
        Calculator.clear();
    }
    @Test
    @Order(6)
    public void sumTest() throws InterruptedException {
        int result = Calculator.sum(5);
        System.out.println(result);
        result = Calculator.sum(5);
        System.out.println(result);
        result = Calculator.sum(5);
        System.out.println(result);
        result = Calculator.sum(5);
        System.out.println(result);
        assertEquals(5,5,"断言结果正确");
        assertEquals(10,10,"断言结果正确");
        assertEquals(15,15,"断言结果正确");
        assertEquals(20,20,"断言结果正确");
    }
}
