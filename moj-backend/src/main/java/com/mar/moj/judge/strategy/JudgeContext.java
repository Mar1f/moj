package com.mar.moj.judge.strategy;

import com.mar.moj.model.dto.question.JudgeCase;
import com.mar.moj.model.dto.questionsubmit.JudgeInfo;
import com.mar.moj.model.entity.Question;
import lombok.Data;

import java.util.List;

/**
 * @description；用于定义在策略中传递的参数
 * @author:mar1
 * @data:2024/08/30
 **/
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;
}
