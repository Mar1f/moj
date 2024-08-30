package com.mar.moj.judge.codesandbox.impl;

import com.mar.moj.judge.codesandbox.CodeSandbox;
import com.mar.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mar.moj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @description；跑通流程
 * @author:mar1
 * @data:2024/08/29
 **/
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse execute(ExecuteCodeRequest executeCodeRequest) {
        log.info("请求信息： "+executeCodeRequest.toString());
        System.out.println("示例代码信息");
        return null;
    }
}
