package protocol.http;

import framework.Invocation;
import framework.URL;
import org.apache.commons.io.IOUtils;
import register.MapRegister;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 处理具体类别的http 请求
public class HttpServerHandler {

    /**
     * 从 req 里面得到这些东西
     * private String intefaceName;
     * private String methodName;
     * private Object[] params;
     * private Class[] paramTypes;
     *
     * @param req
     * @param resp
     */
    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        try {
            InputStream inputStream = req.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(inputStream);
            // 调用对象
            Invocation invocation = (Invocation) ois.readObject();

            URL url = new URL("localhost", 8080);
            // 从http 请求里面得到要调用的接口名，
            Class implClass = MapRegister.get(invocation.getIntefaceName(), url);

            // 通过反射，根据方法名和参数类型得到具体的实现方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            // 传入方法 和 参数
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            // 通过工具类，让 resp 把结果返回 客户端
            IOUtils.write(result, resp.getOutputStream());

            System.out.println("Tomcat : " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
