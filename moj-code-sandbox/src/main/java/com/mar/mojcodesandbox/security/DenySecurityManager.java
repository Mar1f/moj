package com.mar.mojcodesandbox.security;

import java.security.Permission;

/**
 * @description；
 * @author:mar1
 * @data:2024/09/04
 **/
public class DenySecurityManager extends SecurityManager{
    @Override
    public void checkPermission(Permission permission) {
        throw new SecurityException("Permission denied in :" + permission.toString());
    }
}
