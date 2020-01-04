package provider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboProviderDemo {
    class A {

    }

    class B extends A {

    }

    public void f() {
        B b = new B();
        A a = new A();
        a =b;
    }

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderDemo.class, args);
    }


}
