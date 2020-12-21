package junit5_rest_assured.ceremonial;

import io.restassured.response.Response;
import junit5_rest_assured.apiobject.DepartmentObject;
import junit5_rest_assured.apiobject.TokenHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @Description: java类作用描述
 * 创建部门并发测试,同样的入参在并发的情况下只能创建一次成功
 * 创建部门有排重逻辑,所以需要进行高并发测试
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 17:30$
 */
public class Demo_07_thread_createDepartment {

    static String access_token;

    @BeforeAll
    static void getAccessTokenTest() {
        access_token = TokenHelper.getToken();//调用封装好的getToken方法
    }

    @Test
    @DisplayName("创建部门")
    @RepeatedTest(100)//执行用例100次,根据junit-platform.properties中配置的,每次并发为10次
    @Execution(CONCURRENT)//支持多线程
    public void creatDepartment() {
        String name = "君子堂" ;
        String name_en = "JZT" ;
        //调用封装类DepartmentObject中creatDepartmentObject方法,优化代码
        Response createDepartmentResponse = DepartmentObject.creatDepartmentObject(name, name_en, access_token);
        assertEquals("0", createDepartmentResponse.path("errcode").toString());

    }
}
