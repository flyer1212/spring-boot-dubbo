package consumer;


import apidemo.DemoService;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Reference(version = "1.0.0", url ="dubbo://127.0.0.1:8087")
    private DemoService demoService;

    @GetMapping("hello/{name}")
    public Object getHello(@PathVariable String name) {
        return demoService.sayHello(name);
    }
}
