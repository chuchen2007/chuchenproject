package junit5_rest_assured.classic;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Rest_AssuredClassicTest {
    public static String access_token;

    @BeforeAll
    static void getAccessTokenTest() {
        access_token = given()
                .params("corpid", "wwfb82047de106c34e", "corpsecret", "vUeru60DD9A8rd46eyd4iGZiMLAm3A3-o6FAxRQkNQM")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
    }

    @Test
    @Order(1)
    void SendMessageClassicTest() {
        given()
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"倚楼听风雨，淡看江湖路.\"" +
                        "   },\n" +
                        "}")
                .queryParams("access_token", access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();
    }

    @Test
    void addDepartmentClassicTest(){
        access_token = given()
                .params("corpid", "wwfb82047de106c34e", "corpsecret", "qggo1ZS1MJnCl4Fz59bEfdTDoo8CVOioCqtT4BK2i1Y")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");

        given()
                .when()
                .contentType("application/json;charset=UTF-8")
                .body("{\n" +
                        "   \"name\": \"归云庄\",\n" +
                        "   \"name_en\": \"GYZ\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1,\n" +
                        "   \"id\": \n" +
                        "}")
                .queryParams("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all();

    }
}
