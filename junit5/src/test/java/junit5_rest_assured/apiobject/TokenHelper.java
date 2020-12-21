package junit5_rest_assured.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @Description: java类作用描述
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 16:25$
 */
public class TokenHelper {
    public static String getToken(){
        String access_token = given()
                .params("corpid", "wwfb82047de106c34e", "corpsecret", "qggo1ZS1MJnCl4Fz59bEfdTDoo8CVOioCqtT4BK2i1Y")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
        return access_token;
    }
}
