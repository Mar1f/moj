package com.mar.mojbackendjudgeservice.controller.innner;


import com.mar.mojbackendjudgeservice.judge.JudgeService;
import com.mar.mojbackendmodel.model.entity.QuestionSubmit;
import com.mar.mojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description；内部调用服务
 * @author:mar1
 * @data:2024/09/14
 **/
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;
    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId){
        return judgeService.doJudge(questionSubmitId);
    }

}
