package com.mar.moj.judge.codesandbox.impl;

import com.mar.moj.judge.codesandbox.CodeSandbox;
import com.mar.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mar.moj.judge.codesandbox.model.ExecuteCodeResponse;
import com.mar.moj.judge.codesandbox.model.JudgeInfo;
import com.mar.moj.model.enums.JudgeInfoMessageEnum;
import com.mar.moj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description；跑通流程
 * @author:mar1
 * @data:2024/08/29
 **/
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
