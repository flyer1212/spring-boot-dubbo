package framework;

import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

// 工厂方法， 动态切换使用http 还是 netty
public class ProtocolFactory {

    public static Protocol  getProtocol(){
        // -DprotocolName=dubbo -DprotocolName=http
        // 通过配置
        String name = System.getProperty("protocolName");
        if(name == null || name.equals(""))
            name = "http";

        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }

        return new HttpProtocol();
    }

}
