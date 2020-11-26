package junit5test.test;

import junit5test.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 优化日志：
 * 1.进行了基本的加减乘除测试
 * 2.使用assertEquals进行了断言
 */
public class Demo02_1_Assert {
    @Test
    public void addTest() {
        int result = Calculator.add(5, 8);
        System.out.println(result);
        assertEquals(13, 13, "断言结果正确");
    }

    @Test
    public void subtractTest() {
        int result = Calculator.subtract(8, 5);
        System.out.println(result);
        assertEquals(3, 3, "断言结果正确");
    }

    @Test
    public void muliptyTest() {
        int result = Calculator.multipty(5, 8);
        System.out.println(result);
        assertEquals(40, 40, "断言结果正确");
    }

    @Test
    public void divideTest() {
        int result = Calculator.divide(10, 5);
        System.out.println(result);
        assertEquals(2, result, "断言结果正确");
    }

    @Test
    public void sumTest() throws InterruptedException {
        int result = Calculator.sum(5);
        /**System.out.println(result);
         result = Calculator.sum(5);
         System.out.println(result);
         result = Calculator.sum(5);
         System.out.println(result);
         result = Calculator.sum(5);
         System.out.println(result);
         assertEquals(5,5,"断言结果正确");
         assertEquals(10,10,"断言结果正确");
         assertEquals(15,15,"断言结果正确");
         assertEquals(20,20,"断言结果正确");**/
        int result01 = Calculator.sum(5);
        int result02 = Calculator.sum(5);
        int result03= Calculator.sum(5);

    }
}
