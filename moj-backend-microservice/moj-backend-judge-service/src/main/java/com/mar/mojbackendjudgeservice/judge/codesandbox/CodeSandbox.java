package com.mar.mojbackendjudgeservice.judge.codesandbox;


import com.mar.mojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mar.mojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
