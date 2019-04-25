package provider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DubboProviderDemo {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderDemo.class, args);
    }
}
