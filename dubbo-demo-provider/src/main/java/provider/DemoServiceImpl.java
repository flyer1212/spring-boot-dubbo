package provider;

import apidemo.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("Hello :" + name);
        return  "Hello :" +name;
    }
}
