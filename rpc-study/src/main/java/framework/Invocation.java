package framework;
// 调用对象

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Invocation  implements Serializable {

    private String intefaceName;
    private String methodName;
    private Object[] params;
    private Class[] paramTypes;

}
