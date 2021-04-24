package com.longlysmile.util;

import com.longlysmile.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * TODO Shiro工具类
 *
 * @author wujie
 * @version 1.0
 * @date 2020/11/22 22:23
 */
public class ShiroUtil {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
