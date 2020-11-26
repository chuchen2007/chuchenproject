package junit5test.test;

import junit5test.Calculator;
import org.junit.jupiter.api.RepeatedTest;

public class Demo04_1_Parallel {
    @RepeatedTest(10)
    public void testSum(){
        try {
            int result = Calculator.sum(5);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
       }
    }
}