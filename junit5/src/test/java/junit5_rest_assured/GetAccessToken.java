package junit5_rest_assured;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAccessToken {
    public static String access_token;
    @BeforeAll
    static  void testGetAccessToken(){
        access_token=given()
                .params("corpid","wwfb82047de106c34e","corpsecret","vUeru60DD9A8rd46eyd4iGZiMLAm3A3-o6FAxRQkNQM")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
    }
    @Test
    void testSendMessage(){
        given()
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"Mine fat m,my love.倚楼听风雨，淡看江湖路。\"" +
                        "   },\n" +
                        "}")
                .queryParams("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();
    }

}
