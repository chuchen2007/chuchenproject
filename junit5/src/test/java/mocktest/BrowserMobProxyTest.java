package mocktest;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BrowserMobProxyTest {
    public static BrowserMobProxy proxy;
    @Test
    public void BmpTest() throws IOException {
         proxy = new BrowserMobProxyServer();
        proxy.start(8888);
        int port = proxy.getPort();
//

        proxy.addResponseFilter(((httpResponse, httpMessageContents, httpMessageInfo) -> {
            if(httpMessageInfo.getOriginalUrl().contains(".json")){
                //todo:json-> hashmap -> rescue -> hashmap ->json
                String contentNew = httpMessageContents.getTextContents().replaceAll(":\"[^\"]*\"","null");
                httpMessageContents.setTextContents(contentNew);
            }
        }));

        proxy.addRequestFilter(((httpRequest, httpMessageContents, httpMessageInfo) -> {
            httpRequest.setUri("/");
            return null;
        }));

        System.in.read();

    }
}
