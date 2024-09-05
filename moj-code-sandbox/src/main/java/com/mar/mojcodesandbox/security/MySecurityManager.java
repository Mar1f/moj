package com.mar.mojcodesandbox.security;

import java.security.Permission;

/**
 * @description；
 * @author:mar1
 * @data:2024/09/04
 **/
public class MySecurityManager extends SecurityManager{
    //检查所有权限
    @Override
    public void checkPermission(Permission permission) {
        System.out.println("默认无限制");
//        super.checkPermission(permission);

    }

    // 检查是否允许执行文件
    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("不允许执行文件" + cmd);
//        super.checkExec(cmd);
    }

    // 检查是否允许读文件
    @Override
    public void checkRead(String file) {
        throw new SecurityException("不允许读文件"+ file);
//        super.checkRead(file);
    }

    // 检查是否允许写文件
    @Override
    public void checkWrite(String file) {
        throw new SecurityException("不允许写文件"+file);
//        super.checkWrite(file);
    }

    //检查是否允许删除文件
    @Override
    public void checkDelete(String file) {
        throw new SecurityException("不允许删除文件"+file);
//        super.checkDelete(file);
    }

    //检查是否允许链接网络
    @Override
    public void checkConnect(String host, int port) {
        throw new SecurityException("不允许链接网络"+ host + ":"+ port);
//        super.checkConnect(host, port);
    }
}
