package com.mar.mojcodesandbox.security;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @description；
 * @author:mar1
 * @data:2024/09/04
 **/
public class TestSecurityManager {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
//        List<String> strings = FileUtil.readLines("F:\\java\\code\\moj\\moj-code-sandbox\\src\\main\\resources\\application.yml", StandardCharsets.UTF_8);
        FileUtil.writeString("aa","aaa",StandardCharsets.UTF_8);
//        System.out.println(strings);
    }
}
