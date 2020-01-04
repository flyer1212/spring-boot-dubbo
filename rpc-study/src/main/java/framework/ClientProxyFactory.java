package framework;

import protocol.http.HttpClient;
import register.MapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 动态代理
// 用于实现client 像调用本地代码一样
public class ClientProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//                HttpClient httpClient = new HttpClient();
//                // 消费者必须可以使用到接口的j'a'r 包的
//                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());
//
//                String result = httpClient.post("localhost", 8080, invocation);


                // 下面通过协议决定采样什么方式发送数据
                Protocol protocol = ProtocolFactory.getProtocol();
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());
                System.out.println(MapRegister.randomUrl(interfaceClass.getName().toString()));
                String result = protocol.send(MapRegister.randomUrl(interfaceClass.getName()), invocation);
                return result;
            }
        });
    }

}
