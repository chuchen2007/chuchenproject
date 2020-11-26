package junit5test.test;

import junit5test.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo4_3_AtomicInteger {
    @RepeatedTest(10)
    public void testSumAt0() throws InterruptedException{
        AtomicInteger result = Calculator.sumAt0Test(5);
        System.out.println(result);
    }
}
