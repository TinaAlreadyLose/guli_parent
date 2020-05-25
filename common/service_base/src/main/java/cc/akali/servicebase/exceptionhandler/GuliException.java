package cc.akali.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: dan
 * @Date: 2020/4/29 21:38
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor //生成有参构造方法
@NoArgsConstructor //生成无参构造方法
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
