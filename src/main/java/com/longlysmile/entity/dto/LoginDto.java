package com.longlysmile.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TODO 登录实体类
 *
 * @author wujie
 * @version 1.0
 * @date 2020/11/22 18:45
 */
@Data
public class LoginDto implements Serializable {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

}
