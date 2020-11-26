package junit5test;

import java.util.concurrent.atomic.AtomicInteger;

public class Calculator {
    public static int result = 0;
    public static AtomicInteger result02 = new AtomicInteger();

    public  static AtomicInteger  sumAt0Test(int x)throws InterruptedException{
        result02.addAndGet(x);
        Thread.sleep(1000);
        return result02;
    }

    public static int add(int x, int y) {
        result = x + y;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int subtract(int x, int y) {
        result = x - y;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int multipty(int x, int y) {
        result = x * y;
        return result;
    }

    public static int divide(int x, int y) {
        result = x / y;
        return result;
    }

    public static void clear(){
        result = 0;
        System.out.println("当前结果已清零");
    }

    public static int sum(int x) throws InterruptedException {
        int i =result;
        Thread.sleep(1000);
        result = i +x;
        return result;
    }

}
