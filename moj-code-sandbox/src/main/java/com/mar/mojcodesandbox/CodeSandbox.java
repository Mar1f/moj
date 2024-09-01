package com.mar.mojcodesandbox;

import com.mar.mojcodesandbox.model.ExecuteCodeRequest;
import com.mar.mojcodesandbox.model.ExecuteCodeResponse;

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
