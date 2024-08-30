package com.mar.moj.judge;

import cn.hutool.json.JSONUtil;
import com.github.mustachejava.Code;
import com.mar.moj.common.ErrorCode;
import com.mar.moj.common.PageRequest;
import com.mar.moj.exception.BusinessException;
import com.mar.moj.judge.codesandbox.CodeSandbox;
import com.mar.moj.judge.codesandbox.CodeSandboxFactory;
import com.mar.moj.judge.codesandbox.CodeSandboxProxy;
import com.mar.moj.judge.codesandbox.model.ExecuteCodeRequest;
import com.mar.moj.judge.codesandbox.model.ExecuteCodeResponse;
import com.mar.moj.model.dto.question.JudgeCase;
import com.mar.moj.model.dto.question.JudgeConfig;
import com.mar.moj.model.entity.Question;
import com.mar.moj.model.entity.QuestionSubmit;
import com.mar.moj.model.enums.JudgeInfoMessageEnum;
import com.mar.moj.model.enums.QuestionSubmitLanguageEnum;
import com.mar.moj.model.enums.QuestionSubmitStatusEnum;
import com.mar.moj.model.vo.QuestionSubmitVO;
import com.mar.moj.service.QuestionService;
import com.mar.moj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description；
 * @author:mar1
 * @data:2024/08/30
 **/
@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmitVO doJudge(long questionSubmitId) {
        // 获取题目的信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if(questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交记录不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if(question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 如果题目提交状态不为等待中，就不用重复执行
        if(!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "该提交记录正在判题中");
        }
        // 更新题目的状态为正在判题中
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if(!update){ // 更新失败
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新失败");
        }
        // 调用代码沙箱
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest
                .builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
        if(outputList.size() != inputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            return null;
        }
        // 判断输出是否正确
        for(int i = 0; i < judgeCaseList.size(); i++){
            JudgeCase judgeCase = judgeCaseList.get(i);
            if(!judgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                return null;
            }
        }


        Long id = questionSubmit.getId();
        String judgeInfo = questionSubmit.getJudgeInfo();
        Integer status = questionSubmit.getStatus();

        Long userId = questionSubmit.getUserId();
        Date createTime = questionSubmit.getCreateTime();
        Date updateTime = questionSubmit.getUpdateTime();
        Integer isDelete = questionSubmit.getIsDelete();

        return null;
    }
}
