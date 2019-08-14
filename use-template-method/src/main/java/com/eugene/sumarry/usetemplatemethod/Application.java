package com.eugene.sumarry.usetemplatemethod;

import com.eugene.sumarry.usetemplatemethod.common.FeignResponseTemplate;
import com.eugene.sumarry.usetemplatemethod.feign.UserService;
import com.eugene.sumarry.usetemplatemethod.feign.client.UserServiceClient;
import com.eugene.sumarry.usetemplatemethod.model.User;

public class Application {

    public static void main(String[] args) {

        // 获取用户信息
        UserService userService = new UserServiceClient();
        new FeignResponseTemplate<User>(userService.getUserInfo()) {
            @Override
            protected void handleException() {
                System.out.println("进入异常处理器");
            }

            @Override
            protected void handleSuccess(User user) {
                System.out.println("成功处理器, 获取消息为: " + user);
            }
        }.getMessage();


        // 获取验证用户是否存在
        new FeignResponseTemplate<Boolean>(userService.checkUserIsExist(11L)) {
            @Override
            protected void handleException() {
                System.out.println("进入异常处理器");
            }

            @Override
            protected void handleSuccess(Boolean aBoolean) {
                System.out.println("成功处理器, 接收到的消息: " + aBoolean);
            }
        }.getMessage();
    }
}
