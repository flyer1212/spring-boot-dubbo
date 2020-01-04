package provider;

import framework.Protocol;
import framework.ProtocolFactory;
import framework.URL;
import protocol.http.HttpServer;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.MapRegister;

public class Provider {
    public static void main(String[] args) {

        // 1. 注册服务
        URL url = new URL("localhost", 8080);
        MapRegister.regist(HelloService.class.getName(), url, HelloServiceImpl.class);

        // 2. 启动Tomcat 或 jetty
//        HttpServer httpServer = new HttpServer();
//        httpServer.start(url.getHostName(), url.getPort());

        // 通过协议决定启动对象
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);

    }
}
