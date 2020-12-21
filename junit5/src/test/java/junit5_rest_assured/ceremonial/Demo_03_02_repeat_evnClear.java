package junit5_rest_assured.ceremonial;

import io.restassured.response.Response;
import junit5_rest_assured.utils.FakerUtils;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description:
 * 1, 基础脚本:分别调用企业微信通讯录API:创建,更新,查询,删除.
 * 2,优化思路:接口之间解耦,做到方法内调用自给自足
 * 3,优化思路:对入参增加时间戳,保证每次入参都不一致,解决每次都需要手动修改数据问题
 * 4,优化思路:设计一个清楚数据的公共方法,在每次执行方法的前后均调用,删除数据,达到加时间戳类似效果
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 16:16$
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03_02_repeat_evnClear {
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
        access_token = given()
                .params("corpid", "wwfb82047de106c34e", "corpsecret", "qggo1ZS1MJnCl4Fz59bEfdTDoo8CVOioCqtT4BK2i1Y")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
    }

    @BeforeEach
    @AfterEach
        //增加删除数据方法,利用junit5注释,在类中每次方法调用前后执行
    void evnClear() {
        //先调用查询方法,查询公司中有多少部门
        Response listDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .queryParams("access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .extract().response();
        assertEquals("0", listDepartmentResponse.path("errcode").toString());
        //获取部门列表,返回的id是一个数组,利用foreach循环,定义一个int类型变量遍历返回的id数组
        ArrayList<Integer> listDepartmentResponseId = listDepartmentResponse.path("department.id");
        for (int evnClearDepartmentId : listDepartmentResponseId) {
            if (1 == evnClearDepartmentId || 5 == evnClearDepartmentId || 6 == evnClearDepartmentId) {
                //1为跟部门,无法删除,5与6部门比较喜欢,不想删除
                continue;
            }
            System.out.print(evnClearDepartmentId + ",");//查看有多少部门
            //将获取的evnClearDepartmentId作为删除部门方法入参的departmentId,达到清楚数据的效果
            Response deleteDepartmentResponse = given().log().all()//查看evnClearDepartmentId入参信息对不对
                    .when()
                    .contentType("application/json")
                    .queryParams("access_token", access_token)
                    .param("id", evnClearDepartmentId)
                    .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                    .then().log().all()
                    .extract().response();
        }
    }


    @Test
    @DisplayName("创建部门")
    @Order(1)
    public void creatDepartment() {
        String name = "极乐谷" + FakerUtils.getTimeStamp();
        String name_en = "JRG" + FakerUtils.getTimeStamp();

        Response createDepartmentResponse = given().log().all()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"" + name + "\",\n" +
                        "   \"name_en\": \"" + name_en + "\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .extract().response();
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
        Response createDepartmentResponse = given().log().all()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"" + name + "\",\n" +
                        "   \"name_en\": \"" + name_en + "\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .extract().response();
        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
        //更新部门
        Response updateDepartmentResponse;
        updateDepartmentResponse = given().log().all()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"id\": " + departmentId + ",\n" +
                        "   \"name\": \"" + name + "\",\n" +
                        "   \"name_en\": \"" + name_en + "\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all()
                .extract().response();
        assertEquals("0", updateDepartmentResponse.path("errcode").toString());
    }


    @DisplayName("查询部门列表")
    @Test
    @Order(3)
    void listDepartment() {
        //查询部门接口api不存在数据问题,不在本次加时间戳优化之列,决定不删除一些固定的部门,所以不需要重新添加创建部门方法
        Response listDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .queryParams("access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
        assertEquals("0", listDepartmentResponse.path("errcode").toString());
    }

    @Test
    @DisplayName("删除部门")
    @Order(4)
    void deleteDepartment() {
        String name = "极乐谷" + FakerUtils.getTimeStamp();
        String name_en = "JRG" + FakerUtils.getTimeStamp();
        //先创建部门,后删除部门
        Response createDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"" + name + "\",\n" +
                        "   \"name_en\": \"" + name_en + "\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .extract().response();
        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
        //删除部门
        Response deleteDepartmentResponse = given()
                .when()
                .contentType("application/json")
                .queryParams("access_token", access_token, "id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        assertEquals("0", deleteDepartmentResponse.path("errcode").toString());
    }

}
