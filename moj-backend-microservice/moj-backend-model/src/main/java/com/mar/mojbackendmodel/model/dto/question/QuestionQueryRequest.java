package com.mar.mojbackendmodel.model.dto.question;

import com.mar.mojbackendcommon.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

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
     * 标签列表
     */
    private List<String> tags;

    /**
     * 难度
     */
    private String difficulty;
    /**
     * 题目答案
     */
    private String answer;

    /**
     * 提交用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}