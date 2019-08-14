package com.eugene.sumarry.notemplatemethod;

import com.eugene.sumarry.notemplatemethod.feign.UserService;
import com.eugene.sumarry.notemplatemethod.web.FeignMessage;
import com.eugene.sumarry.notemplatemethod.constants.Constants;
import com.eugene.sumarry.notemplatemethod.feign.client.UserServiceClient;
import com.eugene.sumarry.notemplatemethod.model.User;

public class Application {

    public static void main(String[] args) {

        // 获取用户信息
        UserService userService = new UserServiceClient();

        FeignMessage feignMessage1 = userService.getUserInfo();
        if (feignMessage1.getCode() != null) {
            System.out.println("Feign client 发生业务异常, 异常信息为: " + feignMessage1.getMessage());
        } else {
            User user = feignMessage1.getAttribute(Constants.FEIGN_CLIENT_DATA_KEY, User.class);
            System.out.println(user);
        }


        // 获取验证用户是否存在
        FeignMessage feignMessage2 = userService.checkUserIsExist(11L);
        if (feignMessage2.getCode() != null) {
            System.out.println("Feign client 发生业务异常, 异常信息为: " + feignMessage2.getMessage());
        } else {
            boolean isExist = feignMessage2.getAttribute(Constants.FEIGN_CLIENT_DATA_KEY, Boolean.class);
            System.out.println(isExist);
        }
    }
}
