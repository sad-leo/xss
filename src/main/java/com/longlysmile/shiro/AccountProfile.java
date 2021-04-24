package com.longlysmile.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2020/10/25 22:37
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
}