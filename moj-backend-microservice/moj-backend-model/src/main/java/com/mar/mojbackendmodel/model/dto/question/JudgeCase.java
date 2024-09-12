package com.mar.mojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @description；题目用例
 * @author:mar1
 * @data:2024/08/06
 **/
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
