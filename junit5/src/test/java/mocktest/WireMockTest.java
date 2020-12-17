package mocktest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockTest {
    public static WireMockServer wireMockServer;

    @BeforeAll
    public static void BeforeAll() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8888));
//        wireMockServer = new WireMockServer(); //必须按照以上官网写法
        wireMockServer.start();
        configureFor("localhost",8888);
    }

    @Test
    public void stubTest  () throws InterruptedException {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>淡看江湖路</response>")));
        Thread.sleep(50000);
    }

    @Test
    public void easyMock()throws InterruptedException{
        stubFor(get(urlEqualTo("/ceshiren/chuchen"))
                .withHeader("Accept",equalTo("text/xml"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type","text/xml")
                        .withBody("倚楼听风雨")));
        Thread.sleep(10000);
        reset();
        stubFor(get(urlEqualTo("/ceshiren/chuchen"))
                .withHeader("Accept",equalTo("text/xml"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type","text/xml")
                        .withBody("淡看江湖路")));
        Thread.sleep(50000);
    }
    @Test
    public void proxyMockTest(){
        try {
            stubFor(get(urlMatching(".*")).atPriority(10)
                    .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));
//利用proxy,将指定url替换成本地url
//            stubFor(get(urlEqualTo("categories_and_latest")).atPriority(1)
//                    .willReturn(aResponse().withStatus(503)));

                stubFor(get(urlEqualTo("/categories_and_latest")).atPriority(1)
                        //通过json文件修改指定网页信息
                        .willReturn(aResponse().withBody(Files.readAllBytes(Paths
                                .get(WireMockTest.class.getResource("/chuchenmock.json")
                                        .toURI())))));
            Thread.sleep(50000);
        } catch (Exception  e) {
            e.printStackTrace();

        }

    }

    @Test
    public void printJsonTest(){
        File f = new File(this.getClass().getResource("/chuchenmock.json").getPath());
        System.out.println(f);
    }
}
