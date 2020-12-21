package junit5_rest_assured.utils;

/**
 * @Description: 封装常用的数据类
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 17:25$
 */
public class FakerUtils {
    //获取时间戳
    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }

}
