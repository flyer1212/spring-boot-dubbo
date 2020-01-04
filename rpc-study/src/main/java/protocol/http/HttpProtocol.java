package protocol.http;

import framework.Invocation;
import framework.Protocol;
import framework.URL;

// 通过协议，可以在使用得时候，灵活选择是http 还是 dubbo
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.post(url.getHostName(), url.getPort(), invocation);
    }
}
