package framework;

// 定义一个协议得抽象
// 现在有了http , netty

public interface Protocol {

    // 启动 netty  或 tomcat
    void start(URL url);

    // 发送数据
    String send(URL url, Invocation invocation);
}