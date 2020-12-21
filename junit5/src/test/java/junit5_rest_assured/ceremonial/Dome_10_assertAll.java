package junit5_rest_assured.ceremonial;

import io.restassured.response.Response;
import junit5_rest_assured.apiobject.DepartmentObject;
import junit5_rest_assured.apiobject.TokenHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description: java类作用描述
 * 1,基础脚本:分别调用企业微信通讯录API:创建,更新,查询,删除.
 * 2,优化思路:接口之间解耦,做到方法内调用自给自足
 * 3,优化思路:对入参增加时间戳,保证每次入参都不一致,解决每次都需要手动修改数据问题
 * 4,优化思路:设计一个清楚数据的公共方法,在每次执行方法的前后均调用,删除数据,达到加时间戳类似效果
 * 5,优化思路:对逻辑进行分层封装,减少重复代码,利于维护
 * 6,优化思路:数据驱动,测试数据与测试代码分离
 * 7,优化思路:Java  8 lambdas的软断言方法assertAll进行断言,解决一个方法断言失败,后续断言都没有执行
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 17:10$
 */
public class Dome_10_assertAll {
    //使用获取部门接口,来验证软断言
    static String access_token;

    @BeforeAll
    static void getAccessTokenTest() {
        access_token = TokenHelper.getToken();//调用封装好的getToken方法
    }

    @DisplayName("查询部门列表")
    @Test
    void listDepartment() {
        //查询部门接口api不存在数据问题,不在本次加时间戳优化之列,决定不删除一些固定的部门,所以不需要重新添加创建部门方法
        //调用封装类DepartmentObject中listDepartmentResponse方法,优化代码
        Response listDepartmentResponse = DepartmentObject.listDepartmentObject(access_token);
        //单断言方式,如果中间有错误,后续不执行
//        assertEquals("0", listDepartmentResponse.path("errcode").toString());
//        assertEquals("ok", listDepartmentResponse.path("errmsg").toString());
//        assertEquals("1", listDepartmentResponse.path("department.id[0]").toString());
//        assertEquals("5", listDepartmentResponse.path("department.id[1]").toString());
//        assertEquals("6", listDepartmentResponse.path("department.id[2]").toString());

        assertAll("软断言",//错误继续执行后续断言
                ()-> assertEquals("0", listDepartmentResponse.path("errcode").toString()),
                ()-> assertEquals("ok", listDepartmentResponse.path("错误断言").toString()),
                ()-> assertEquals("1", listDepartmentResponse.path("错误断言").toString()),
                ()-> assertEquals("5", listDepartmentResponse.path("department.id[1]").toString()),
                ()-> assertEquals("6", listDepartmentResponse.path("department.id[2]").toString())
        );
    }
}
