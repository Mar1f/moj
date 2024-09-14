package com.mar.mojbackenduserservice.controller.inner;

import com.mar.mojbackendmodel.model.entity.User;
import com.mar.mojbackendserviceclient.service.UserFeignClient;
import com.mar.mojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @description；内部调用服务
 * @author:mar1
 * @data:2024/09/14
 **/
@RestController("/inner")
public class UserInnerController implements UserFeignClient {
    @Resource
    private UserService userService;
    /**
     * 根据id获取用户
     *
     * @param userId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") long userId){
        return userService.getById(userId);

    }

    /**
     * 根据id获取用户列表
     * @param idList
     * @return
     */
    @Override
    @GetMapping("/get/ids")
    public List<User> listByIds(@RequestParam("idList")Collection<Long> idList){
        return userService.listByIds(idList);

    }

}
