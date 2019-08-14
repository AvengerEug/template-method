package com.eugene.sumarry.usetemplatemethod.common;

import com.eugene.sumarry.usetemplatemethod.constants.Constants;
import com.eugene.sumarry.usetemplatemethod.web.FeignMessage;

/**
 * 获取FeignMessage信息的模板方法
 * @param <T>
 */
public abstract class FeignResponseTemplate<T> {

    private FeignMessage feignMessage;

    public FeignResponseTemplate() {
    }

    public FeignResponseTemplate(FeignMessage feignMessage) {
        this.feignMessage = feignMessage;
    }

    protected abstract void handleException();

    protected abstract void handleSuccess(T t);

    public final void getMessage() {
        if (feignMessage.getCode() != null) {
            System.out.println("Feign client 发生业务异常, 异常代码: code = " + feignMessage.getCode() +  " 信息为: " + feignMessage.getMessage());
            this.handleException();
        } else {
            this.handleSuccess((T)feignMessage.getAttribute(Constants.FEIGN_CLIENT_DATA_KEY));
        }
    }
}
