package com.mar.mojbackendjudgeservice.judge;

import com.mar.mojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.mar.mojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.mar.mojbackendjudgeservice.judge.strategy.JudgeContext;
import com.mar.mojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.mar.mojbackendmodel.model.codesandbox.JudgeInfo;
import com.mar.mojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
