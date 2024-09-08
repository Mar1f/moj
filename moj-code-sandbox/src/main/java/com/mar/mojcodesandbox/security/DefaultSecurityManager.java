package com.mar.mojcodesandbox.security;

import java.security.Permission;

/**
 * @description；
 * @author:mar1
 * @data:2024/09/04
 **/
public class DefaultSecurityManager extends SecurityManager{
    @Override
    public void checkPermission(Permission permission) {
        System.out.println("默认不做任何限制");
        System.out.println(permission);
//        super.checkPermission(permission);

    }
}
