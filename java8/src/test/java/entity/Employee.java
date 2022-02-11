package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SpringCloudStudy
 * @description: 测试类
 * @author: Mr.Pu
 * @create: 2022-01-24 20:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer code;
    private String name;
    private Integer size;
    private double price;

}
