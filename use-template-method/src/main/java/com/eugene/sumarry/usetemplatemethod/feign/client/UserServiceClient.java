package com.eugene.sumarry.usetemplatemethod.feign.client;

import com.eugene.sumarry.usetemplatemethod.common.FeignRequestTemplate;
import com.eugene.sumarry.usetemplatemethod.feign.UserService;
import com.eugene.sumarry.usetemplatemethod.model.User;
import com.eugene.sumarry.usetemplatemethod.web.FeignMessage;

/**
 * 模拟用户服务client, 实际上的微服务为接口, 这里用实现类代替
 */
public class UserServiceClient implements UserService {

    public FeignMessage getUserInfo() {
        return new FeignRequestTemplate<User>() {
            protected User fetchData() {
                // 模拟通过dao层获取用户数据并返回
                return new User(1L, "Eugene", 23);
            }
        }.initRequest();
    }

    public FeignMessage checkUserIsExist(Long userId) {
        return new FeignRequestTemplate<Boolean>() {
            protected Boolean fetchData() {
                // 这里模拟发生除数不能为0的异常
                boolean isExist = 1 / (2 >> 2) == 1;
                return isExist;
            }
        }.initRequest();
    }
}
