package junit5test.test;

import junit5test.Calculator;
import org.junit.jupiter.api.Test;

/**
 * 优化日志：
 * 1.进行了基本的加减乘除测试
 *
 */
public class Demo01_base {
    @Test
    public void addTest() {
        int result = Calculator.add(5, 8);
        System.out.println(result);
    }

    @Test
    public void subtractTest() {
        int result = Calculator.subtract(8, 5);
        System.out.println(result);
    }

    @Test
    public void muliptyTest() {
        int result = Calculator.multipty(5, 8);
        System.out.println(result);
    }

    @Test
    public void divideTest() {
        int result = Calculator.divide(10, 5);
        System.out.println(result);
    }

    @Test
    public void sumTest() throws InterruptedException {
        int result = Calculator.sum(5);
        System.out.println(result);
        result = Calculator.sum(5);
        System.out.println(result);
        result = Calculator.sum(5);
        System.out.println(result);
        result = Calculator.sum(5);
        System.out.println(result);
    }
}
