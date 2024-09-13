package com.mar.mojbackendjudgeservice.judge.codesandbox;


import com.mar.mojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mar.mojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * @description；
 * @author:mar1
 * @data:2024/08/29
 **/
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
