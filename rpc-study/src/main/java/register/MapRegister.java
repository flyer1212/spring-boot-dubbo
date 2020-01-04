package register;

import framework.URL;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里client 和 provider 模拟的两个java 进程， 共享注册中心 通过文件的方式
 * <p>
 * zookeeper
 * redis
 */
public class MapRegister {
    // 服务名： URL 和对应的实现类
    private static Map<String, Map<URL, Class>> REGISTER = new HashMap<>();

    // 注册方法
    public static void regist(String interfaceName, URL url, Class implClass) {

        Map<URL, Class> map = new HashMap<>();
        map.put(url, implClass);

        REGISTER.put(interfaceName, map);

        try {
            saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 返回具体的实现类
    public static Class get(String interfaceName, URL url) throws IOException, ClassNotFoundException {
        REGISTER = getFile();
        return REGISTER.get(interfaceName).get(url);
    }

    // cliennt 通过接口名，负载均衡的进行调用
    public static URL randomUrl(String interfaceName) throws IOException, ClassNotFoundException {
        // 这里可以写负载均衡，默认拿第一个接口
        REGISTER = getFile();
        return REGISTER.get(interfaceName).keySet().iterator().next();
    }

    private static Map<String, Map<URL, Class>> getFile() throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("G:\\ideaWSP\\dubbo-test\\rpc-study\\src\\main\\java\\framework\\register.txt");
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        return (Map<String, Map<URL, Class>>) ois.readObject();
    }


    private static void saveFile() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("G:\\ideaWSP\\dubbo-test\\rpc-study\\src\\main\\java\\framework\\register.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(REGISTER);
    }
}
