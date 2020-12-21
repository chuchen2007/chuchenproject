package junit5_rest_assured.ceremonial;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description: 1, 基础脚本:分别调用企业微信通讯录API:创建,更新,查询,删除.
 * 2,优化思路:接口之间解耦,做到方法内调用自给自足
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 15:57$
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_02_separate {
    static String access_token;
    static String departmentId;

    @BeforeAll
    static void getAccessTokenTest() {
        access_token = given()
                .params("corpid", "wwfb82047de106c34e", "corpsecret", "qggo1ZS1MJnCl4Fz59bEfdTDoo8CVOioCqtT4BK2i1Y")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
    }

    @Test
    @DisplayName("创建部门")
    @Order(1)
    public void creatDepartment() {
        Response createDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"极乐谷\",\n" +
                        "   \"name_en\": \"JLG\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1,\n" +
                        "   \"id\": " + departmentId + "\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .extract().response();
        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
//创建部门之后删除部门
        Response deleteDepartmentResponse = given()
                .when()
                .contentType("application/json")
                .queryParams("access_token", access_token, "id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        assertEquals("0", deleteDepartmentResponse.path("errcode").toString());
    }

    @Test
    @DisplayName("更新部门")
    @Order(2)
    public void updateDepartment() {
        //先创建部门,后更新部门,后删除部门
        Response createDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"极乐谷\",\n" +
                        "   \"name_en\": \"JLG\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1,\n" +
                        "   \"id\": " + departmentId + "\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .extract().response();
        departmentId = createDepartmentResponse.path("id").toString();
        assertEquals("0", createDepartmentResponse.path("errcode").toString());
        //更新部门
        Response updateDepartmentResponse;
        updateDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"id\": " + departmentId + ",\n" +
                        "   \"name\": \"极乐谷\",\n" +
                        "   \"name_en\": \"JLG\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all()
                .extract().response();
        assertEquals("0", updateDepartmentResponse.path("errcode").toString());
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


    @DisplayName("查询部门列表")
    @Test
    @Order(3)
    void listDepartment() {
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
        //先创建部门,后删除部门
        Response createDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"极乐谷\",\n" +
                        "   \"name_en\": \"JLG\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1,\n" +
                        "   \"id\": " + departmentId + "\n" +
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

