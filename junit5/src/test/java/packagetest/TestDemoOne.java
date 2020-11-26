package packagetest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestDemoOne {
    @Test
    void testgetbaidu(){
        given()
                .get("https://www.baidu.com")
                .then()
                .log().all();

    }


}
