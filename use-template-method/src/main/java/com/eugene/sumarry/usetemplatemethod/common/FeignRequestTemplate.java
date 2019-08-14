package com.eugene.sumarry.usetemplatemethod.common;

import com.eugene.sumarry.usetemplatemethod.constants.Constants;
import com.eugene.sumarry.usetemplatemethod.web.FeignMessage;

/**
 * Feign client message 封装请求，解析请求模板方法
 */
public abstract class FeignRequestTemplate<T> {

    private FeignMessage feignMessage;

    /**
     * 模板方法请求信息
     * @return
     */
    public final FeignMessage initRequest() {

        try {
            // 1. 创建FeignMessage对象
            this.initFeignMessage();

            // 2. 执行具体逻辑获取数据
            this.setAttribute(this.fetchData());
        } catch (Exception e) {
            this.initExceptionInfo(Constants.DEFAULT_ERROR_CODE, e.getMessage());
        }

        return feignMessage;
    }

    private void initExceptionInfo(String code, String message) {
        feignMessage.setCode(code);
        feignMessage.setMessage(message);
    }

    protected abstract T fetchData();

    private void initFeignMessage() {
        this.feignMessage = new FeignMessage();
    }

    private void setAttribute(Object object) {
        try {
            feignMessage.putAttribute(Constants.FEIGN_CLIENT_DATA_KEY, object);
        } catch (Exception e) {
            feignMessage.setCode(Constants.DEFAULT_ERROR_CODE);
            feignMessage.setMessage(e.getMessage());
        }
    }

    public void initFeignMessage(FeignMessage feignMessage) {
        this.feignMessage = feignMessage;
    }
}
