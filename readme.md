## RPC协议
> RPC采用客户端和服务端模式，请求程序的就是客户端
- RPC 协议以传输协议为基础（如TCP,UDP或者HTTP）,为两个不同的应用程序间传递数据，RPC在传输层和应用层之间。


### 目录结构
- api
- consumer （依赖与api）
- provider （依赖与api）

ps: 映入的包全是 apache.dubbo

### provider 实现接口
在 porvider 中， 下面的配置都是必须的。

          spring:
            application:
              name: provider
          dubbo:
            scan:
              base-packages: provider
            protocol:
              name: dubbo
              port: 8087
            registry:
              address: N/A
          server:
            port: 8090
    
- PS： N/A 表示不在zookeeper上注册服务, `服务提供方` 和`消费方` 直接本地ip连接 

### consumer 里面直接调用接口

        @RestController
        public class ConsumerController {
        
            @Reference(version = "1.0.0", url ="dubbo://127.0.0.1:8087")
            private DemoService demoService;
        
            @GetMapping("hello/{name}")
            public Object getHello(@PathVariable String name) {
                return demoService.sayHello(name);
            }
        }
   
### provider 和 consumer 的 全部pom依赖
       <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
              <version>2.0.6.RELEASE</version>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.apache.dubbo</groupId>
              <artifactId>dubbo-spring-boot-starter</artifactId>
              <version>2.7.1</version>
          </dependency>
  
          <dependency>
              <groupId>org.apache.dubbo</groupId>
              <artifactId>dubbo</artifactId>
              <version>2.7.1</version>
          </dependency>

### 启动配置
        @SpringBootApplication
        @EnableAutoConfiguration
### 运行结果
![result](./img/run_result.png)
