package com.mar.moj.mapper;

import com.mar.moj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mar1
* @description 针对表【question_submit(题目提交)】的数据库操作Mapper
* @createDate 2024-08-06 18:41:52
* @Entity com.mar.moj.model.entity.QuestionSubmit
*/
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {
    Integer getPassCount(@Param("userId") Long userId, @Param("ids") List<Long> ids);
}




