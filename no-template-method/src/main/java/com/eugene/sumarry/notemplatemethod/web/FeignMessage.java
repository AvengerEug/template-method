package com.eugene.sumarry.notemplatemethod.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Feign client之间通信规则:
 * code != null => feign client调用发生异常, 需要将异常code(默认使用Constants.DEFAULT_ERROR_CODE)
 *                 和message填充至对应的字段
 *
 * code == null => 无异常信息, 使用putAttribute方式和getAttribute方式传值
 */
public class FeignMessage {

    private String code;
    private String message;
    private Map<String, Object> maps;

    public FeignMessage() {
        maps = new HashMap<String, Object>();
    }

    public FeignMessage(String code, String message) {
        maps = new HashMap<String, Object>();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public void putAttribute(String key, Object value) {
        this.getMaps().put(key, value);
    }

    public <T> T getAttribute(String key, Class<T> clz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this.getMaps().get(key), clz);
    }

    public Object getAttribute(String key) {
        return this.getMaps().get(key);
    }
}
