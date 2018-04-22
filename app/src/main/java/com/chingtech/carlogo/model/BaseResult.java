package com.chingtech.carlogo.model;

/**
 * @author 师春雷
 * @Title: Result
 * @Description:
 * @Company: 北京清软时代科技有限公司
 * @date 2016年8月2日上午10:56:46
 */
public class BaseResult<T> {

    private int code;

    private String message;

    private T result;

    public boolean success() {
        return code == 20001;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
