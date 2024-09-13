package com.mar.mojbackendjudgeservice.judge.strategy;


import com.mar.mojbackendmodel.model.codesandbox.JudgeInfo;
import com.mar.mojbackendmodel.model.dto.question.JudgeCase;
import com.mar.mojbackendmodel.model.entity.Question;
import com.mar.mojbackendmodel.model.entity.QuestionSubmit;
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

    private QuestionSubmit questionSubmit;
}
