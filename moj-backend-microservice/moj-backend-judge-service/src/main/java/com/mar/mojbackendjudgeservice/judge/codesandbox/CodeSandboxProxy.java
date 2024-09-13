package com.mar.mojbackendjudgeservice.judge.codesandbox;

import com.mar.mojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mar.mojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @description；
 * @author:mar1
 * @data:2024/08/30
 **/
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {
    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("请求信息： "+executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息： "+executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
