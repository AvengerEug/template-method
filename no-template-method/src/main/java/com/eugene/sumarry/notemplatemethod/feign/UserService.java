package com.eugene.sumarry.notemplatemethod.feign;

import com.eugene.sumarry.notemplatemethod.web.FeignMessage;

public interface UserService {

    FeignMessage getUserInfo();

    FeignMessage checkUserIsExist(Long userId);
}
