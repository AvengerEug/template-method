package com.eugene.sumarry.usetemplatemethod.feign;

import com.eugene.sumarry.usetemplatemethod.web.FeignMessage;

public interface UserService {

    FeignMessage getUserInfo();

    FeignMessage checkUserIsExist(Long userId);
}
