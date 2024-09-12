package com.mar.mojbackendmodel.model.codesandbox;

import lombok.Data;

/**
 * @description；题目用例
 * @author:mar1
 * @data:2024/08/06
 **/
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 执行时间
     */
    private Long time;
}
