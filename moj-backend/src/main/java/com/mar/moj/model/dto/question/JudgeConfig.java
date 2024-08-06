package com.mar.moj.model.dto.question;

import lombok.Data;

/**
 * @description；题目配置
 * @author:mar1
 * @data:2024/08/06
 **/
@Data
public class JudgeConfig {
    /**
     * 时间限制 ms
     */
    private Long timeLimit;

    /**
     * 空间限制 kb
     */
    private Long memoryLimit;

    /**
     * 堆栈限制 kb
     */
    private Long stackLimit;
}
