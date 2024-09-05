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
        System.out.println("默认无限制");
//        super.checkPermission(permission);

    }

    @Override
    public void checkExec(String cmd) {
        super.checkExec(cmd);
    }

    @Override
    public void checkRead(String file) {
        super.checkRead(file);
    }

    @Override
    public void checkWrite(String file) {
        super.checkWrite(file);
    }

    @Override
    public void checkDelete(String file) {
        super.checkDelete(file);
    }

    @Override
    public void checkConnect(String host, int port) {
        super.checkConnect(host, port);
    }
}
