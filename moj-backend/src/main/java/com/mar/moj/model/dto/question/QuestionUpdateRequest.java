package com.mar.moj.model.dto.question;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新请求
 *
 */
@Data
public class QuestionUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目知识点（json 数组）
     */
    private List<String> tags;

    /**
     * 题目难度（json 数组）
     */
    private List<String> ques_tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 判题用例（json 数组）
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置（json 数组）
     */
    private JudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}