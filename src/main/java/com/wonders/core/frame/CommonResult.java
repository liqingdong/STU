package com.wonders.core.frame;

public class CommonResult<T> {
    private boolean success = true;

    private String message;

    private T body;

    public CommonResult() {

    }

    public CommonResult(boolean success) {
        this.success = success;
    }

    public CommonResult(boolean success, T body) {
        this.success = success;
        this.body = body;
    }

    public CommonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public CommonResult(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public static <T> CommonResult<T> returnSuccess() {
        return new CommonResult<>(true);
    }

    public static <T> CommonResult<T> returnSuccess(T body) {
        return new CommonResult<>(true, body);
    }

    public static <T> CommonResult<T> returnSuccess(String message) {
        return new CommonResult<>(true, message);
    }

    public static <T> CommonResult<T> returnSuccess(String message, T body) {
        return new CommonResult<>(true, message, body);
    }

    public static <T> CommonResult<T> returnFailed() {
        return new CommonResult<>(false);
    }

    public static <T> CommonResult<T> returnFailed(T body) {
        return new CommonResult<>(false, body);
    }

    public static <T> CommonResult<T> returnFailed(String message) {
        return new CommonResult<>(false, message);
    }

    public static <T> CommonResult<T> returnFailed(String message, T body) {
        return new CommonResult<>(false, message, body);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
