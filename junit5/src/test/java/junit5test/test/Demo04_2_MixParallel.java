package junit5test.test;

import junit5test.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Demo04_2_MixParallel {
    @RepeatedTest(10)
    public void testAddTest() {
        int result = Calculator.add(2, 4);
        System.out.println(result);
        assertEquals(6,6);
    }

    @RepeatedTest(10)
    public void testSubtractTest() {
        int result = Calculator.subtract(6, 5);
        System.out.println(result);
        assertEquals(1,1);

    }
}
