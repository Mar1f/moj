package com.mar.mojbackendquestionservice.controller.inner;

import com.mar.mojbackendmodel.model.entity.Question;
import com.mar.mojbackendmodel.model.entity.QuestionSubmit;
import com.mar.mojbackendquestionservice.service.QuestionService;
import com.mar.mojbackendquestionservice.service.QuestionSubmitService;
import com.mar.mojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description；内部调用服务
 * @author:mar1
 * @data:2024/09/14
 **/
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {
    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;


    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }


    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionId") long questionSubmitId){
            return questionSubmitService.getById(questionSubmitId);
    }

    @Override
    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit){
            return questionSubmitService.updateById(questionSubmit);
    }
}
