package junit5_rest_assured.apiobject;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description: java类作用描述
 * @Author: chuchen
 * @CreateDate: 2020/12/21$ 0:22$
 */
public class DepartmentObject {
    public static Response creatDepartmentObject(String name, String name_en, String access_token){
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
        return createDepartmentResponse;
    }

    public static Response updateDepartmentObject(String departmentId, String name , String name_en, String access_token){
        Response updateDepartmentResponse = given().log().all()
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
        return updateDepartmentResponse;
    }

    public static Response listDepartmentObject(String access_token){
        Response listDepartmentResponse = given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .queryParams("access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
        return listDepartmentResponse;
    }

    public static Response deleteDepartmentObject(String access_token,String departmentId){
        Response deleteDepartmentResponse = given()
                .when()
                .contentType("application/json")
                .queryParams("access_token", access_token, "id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        return deleteDepartmentResponse;
    }
}
