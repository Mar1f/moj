package com.mar.mojcodesandbox.security;

import java.security.Permission;

/**
 * @description；
 * @author:mar1
 * @data:2024/09/04
 **/
public class DenySecurityManager extends SecurityManager{
    // 检查所有的权限
    @Override
    public void checkPermission(Permission perm) {
        throw new SecurityException("权限异常：" + perm.toString());
    }
}
