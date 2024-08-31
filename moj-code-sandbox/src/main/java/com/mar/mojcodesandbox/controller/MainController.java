package com.mar.mojcodesandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description；
 * @author:mar1
 * @data:2024/08/31
 **/
@RestController("/")
public class MainController {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
