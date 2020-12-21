package junit5_rest_assured.ceremonial;

import io.restassured.response.Response;
import junit5_rest_assured.apiobject.DepartmentObject;
import junit5_rest_assured.apiobject.TokenHelper;
import junit5_rest_assured.utils.FakerUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description: java类作用描述
 * 对创建部门和更新部门进行并发测试
 * 对数据库有读写操作时,需要考虑读写并发数据库是否会锁表的问题
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 17:33$
 */
public class Demo_08_concurent {
    static String access_token;
    static String departmentId;

    @BeforeAll
    static void getAccessTokenTest() {
        access_token = TokenHelper.getToken();//调用封装好的getToken方法
    }

    @Test
    @DisplayName("创建部门")
    @RepeatedTest(10)
    public void creatDepartment() {
        String  backEndStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp();
        String name = "极乐谷" + backEndStr;
        String name_en = "JRG" + backEndStr;
        //调用封装类DepartmentObject中creatDepartmentObject方法,优化代码
        Response createDepartmentResponse = DepartmentObject.creatDepartmentObject(name, name_en, access_token);

        assertEquals("0", createDepartmentResponse.path("errcode").toString());
    }

    @Test
    @DisplayName("更新部门")
    @RepeatedTest(10)
    public void updateDepartment() {
        //先创建部门,后更新部门
        //通过获取线程号与时间戳的组合,确保每次入参唯一
       String  backEndStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp();
        String name = "极乐谷" + backEndStr;
        String name_en = "JRG" + backEndStr;
        //调用封装类DepartmentObject中creatDepartmentObject方法,优化代码
        Response createDepartmentResponse = DepartmentObject.creatDepartmentObject(name, name_en, access_token);

        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
        //更新部门,调用封装类DepartmentObject中updateDepartmentObject方法,优化代码
        Response updateDepartmentResponse = DepartmentObject.updateDepartmentObject(departmentId, name, name_en, access_token);

        assertEquals("0", updateDepartmentResponse.path("errcode").toString());
    }
}
