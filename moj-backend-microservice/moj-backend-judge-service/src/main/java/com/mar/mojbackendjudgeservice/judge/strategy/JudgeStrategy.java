package com.mar.mojbackendjudgeservice.judge.strategy;

import com.mar.mojbackendmodel.model.codesandbox.JudgeInfo;

/**
 * @description；
 * @author:mar1
 * @data:2024/08/30
 **/
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
