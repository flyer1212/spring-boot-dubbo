package framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class URL  implements Serializable {

    private String hostName;
    private Integer port;

}
