package com.mar.mojbackendjudgeservice.judge;


import com.mar.mojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.mar.mojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.mar.mojbackendjudgeservice.judge.strategy.JudgeContext;
import com.mar.mojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.mar.mojbackendmodel.model.codesandbox.JudgeInfo;
import com.mar.mojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @description；简化对判题功能的调用
 * @author:mar1
 * @data:2024/08/31
 **/
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        // 特判修改策略
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
