package com.eugene.sumarry.notemplatemethod.feign.client;

import com.eugene.sumarry.notemplatemethod.constants.Constants;
import com.eugene.sumarry.notemplatemethod.feign.UserService;
import com.eugene.sumarry.notemplatemethod.model.User;
import com.eugene.sumarry.notemplatemethod.web.FeignMessage;

/**
 * 模拟用户服务client, 实际上的微服务为接口, 这里用实现类代替
 */
public class UserServiceClient implements UserService {

    public FeignMessage getUserInfo() {
        FeignMessage feignMessage = new FeignMessage();

        try {
            feignMessage.putAttribute(Constants.FEIGN_CLIENT_DATA_KEY, new User(1L, "Eugene", 23));
        } catch (Exception e) {
            feignMessage.setCode(Constants.DEFAULT_ERROR_CODE);
            feignMessage.setMessage(e.getMessage());
        }
        return feignMessage;
    }

    public FeignMessage checkUserIsExist(Long userId) {
        FeignMessage feignMessage = new FeignMessage();

        try {
            // 这里模拟发生除数不能为0的异常
            boolean isExist = 1 / (2 >> 2) == 1;
            feignMessage.putAttribute(Constants.FEIGN_CLIENT_DATA_KEY, isExist);
        } catch (Exception e) {
            feignMessage.setCode(Constants.DEFAULT_ERROR_CODE);
            feignMessage.setMessage(e.getMessage());
        }
        return feignMessage;
    }
}
