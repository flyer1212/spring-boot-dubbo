package protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    public void start(String hostName, Integer port) {
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        // getserver 里面赋值名字是tomcat
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostName);

        Host host = new StandardHost();
        host.setName(hostName);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        // 必须要加上一个生命周期监听器
        context.addLifecycleListener(new Tomcat.FixContextListener());


        // 一步一步把上面的依次加到父节点区
        host.addChild(context);
        engine.addChild(host);

        service.addConnector(connector);
        service.setContainer(engine);

        // Tomcat 是处理Servlet的，所以这里定义一个用于处理tomcat请求的Servlet
        tomcat.addServlet(contextPath, "dispatcher",  new DispatchServlet());
        // 符合这个请求路径的，都有这个Servlet来处理
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            // 等待请求过来
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
