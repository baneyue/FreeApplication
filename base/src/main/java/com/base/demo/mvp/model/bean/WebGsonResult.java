package com.base.demo.mvp.model.bean;

public class WebGsonResult<T> {
    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public WebGsonResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public WebGsonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getResult() {
        return result;
    }

    public WebGsonResult<T> setResult(T result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "WebGsonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
