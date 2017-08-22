package com.wonders.core.frame;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 9109817321582420215L;
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public BizException() {
        super();
    }

    public BizException(String errorCode) {
        super("");
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, Throwable cause) {
        super(cause.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
