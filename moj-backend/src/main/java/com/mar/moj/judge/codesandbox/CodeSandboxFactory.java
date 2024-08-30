package com.mar.moj.judge.codesandbox;

import com.mar.moj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.mar.moj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.mar.moj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @description；静态工厂模式
 * @author:mar1
 * @data:2024/08/30
 **/
public class CodeSandboxFactory {
    public static CodeSandbox newInstance(String type) {
       switch (type) {
           case "example":
               return new ExampleCodeSandbox();
           case "remote":
               return new RemoteCodeSandbox();
           case "ThirdParty":
               return new ThirdPartyCodeSandbox();
           default:
               return new ExampleCodeSandbox();
       }
    }
}
