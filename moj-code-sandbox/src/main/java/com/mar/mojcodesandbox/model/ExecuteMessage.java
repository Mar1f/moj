package com.mar.mojcodesandbox.model;

import lombok.Data;

/**
 * @description；进程执行信息
 * @author:mar1
 * @data:2024/09/02
 **/
@Data
public class ExecuteMessage {
    private Integer exitValue;

    private String message;

    private String errorMessage;

    private Long time;
}
