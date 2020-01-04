package consumer;


import framework.ClientProxyFactory;
import provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
        // 动态代理  构造代理对象，封装http 请求
        HelloService helloService = ClientProxyFactory.getProxy(HelloService.class);
        // 上面只是构造了一个代理对象，只有在正在调用具体的方法， 传入具体值得时候，才会真正得去调用
        String result = helloService.sayHello("12345545");
        System.out.println(result);
    }
}
