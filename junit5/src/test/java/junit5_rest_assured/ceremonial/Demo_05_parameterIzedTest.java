package junit5_rest_assured.ceremonial;

import io.restassured.response.Response;
import junit5_rest_assured.apiobject.DepartmentObject;
import junit5_rest_assured.apiobject.TokenHelper;
import junit5_rest_assured.task.EvnClearHeleperTask;
import junit5_rest_assured.utils.FakerUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description: java类作用描述
 * 1,基础脚本:分别调用企业微信通讯录API:创建,更新,查询,删除.
 * 2,优化思路:接口之间解耦,做到方法内调用自给自足
 * 3,优化思路:对入参增加时间戳,保证每次入参都不一致,解决每次都需要手动修改数据问题
 * 4,优化思路:设计一个清楚数据的公共方法,在每次执行方法的前后均调用,删除数据,达到加时间戳类似效果
 * 5,优化思路:对逻辑进行分层封装,减少重复代码,利于维护
 * 6,优化思路:数据驱动,测试数据与测试代码分离
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 16:55$
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_05_parameterIzedTest {
    static String access_token;
    static String departmentId;

    /**
     * name与name_en不能写成静态成员变量,因为更新部门方法中的创建部门中等name与name_en变量的值会与第一个创建部门方法中的变量的值相等,
     * 导致部门后续一个创建部门的方法报错,因为部门已经存在
     * 虽然单独执行以下4个方法没问题,但是类一起执行会报错,为了脚本的健壮性,牺牲脚本的饿可维护性,写成局部变量.
     * static String name = "极乐谷" + FakerUtils.getTimeStamp();
     * static String name_en = "JRG" + FakerUtils.getTimeStamp();
     */

    @BeforeAll
    static void getAccessTokenTest() {
        access_token = TokenHelper.getToken();//调用封装好的getToken方法
    }

//    为了查看参数化结果,注释清楚数据方法
//    @BeforeEach
////    @AfterEach
////        //增加删除数据方法,利用junit5注释,在类中每次方法调用前后执行
////    void evnClear() {
////        //先调用查询方法,查询公司中有多少部门
//////        evnClearHelper.getEvnClear(access_token);封装有缺点,不使用,调用task层封装清理接口
////        EvnClearHeleperTask.evnClearHeleperTask(access_token);//调用封装好的getEvnClear方法,达到删除数据的效果
////    }


    @ParameterizedTest
    @DisplayName("创建部门")
    @Order(1)
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    public void creatDepartment(String name, String name_en, String returnCode) {
        //调用封装类DepartmentObject中creatDepartmentObject方法,优化代码
        Response createDepartmentResponse = DepartmentObject.creatDepartmentObject(name, name_en, access_token);

        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
    }

    @Test
    @DisplayName("更新部门")
    @Order(2)
    public void updateDepartment() {
        //先创建部门,后更新部门
        String name = "极乐谷" + FakerUtils.getTimeStamp();
        String name_en = "JRG" + FakerUtils.getTimeStamp();
        //调用封装类DepartmentObject中creatDepartmentObject方法,优化代码
        Response createDepartmentResponse = DepartmentObject.creatDepartmentObject(name, name_en, access_token);

        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
        //更新部门,调用封装类DepartmentObject中updateDepartmentObject方法,优化代码
        Response updateDepartmentResponse = DepartmentObject.updateDepartmentObject(departmentId, name, name_en, access_token);

        assertEquals("0", updateDepartmentResponse.path("errcode").toString());
    }

    @DisplayName("查询部门列表")
    @Test
    @Order(3)
    void listDepartment() {
        //查询部门接口api不存在数据问题,不在本次加时间戳优化之列,决定不删除一些固定的部门,所以不需要重新添加创建部门方法
        //调用封装类DepartmentObject中listDepartmentResponse方法,优化代码
        Response listDepartmentResponse = DepartmentObject.listDepartmentObject(access_token);
        assertEquals("0", listDepartmentResponse.path("errcode").toString());
    }

    @Test
    @DisplayName("删除部门")
    @Order(4)
    void deleteDepartment() {
        String name = "极乐谷" + FakerUtils.getTimeStamp();
        String name_en = "JRG" + FakerUtils.getTimeStamp();
        //先创建部门,后删除部门
        //调用封装类DepartmentObject中creatDepartmentObject方法,优化代码
        Response createDepartmentResponse = DepartmentObject.creatDepartmentObject(name, name_en, access_token);

        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
        //删除部门
        //调用封装类DepartmentObject中DepartmentObject方法,优化代码
        Response deleteDepartmentResponse = DepartmentObject.deleteDepartmentObject(access_token, departmentId);

        assertEquals("0", deleteDepartmentResponse.path("errcode").toString());
    }
}
