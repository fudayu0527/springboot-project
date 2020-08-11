package com.xiaojiangtun.exception;


/**
 * 自定义异常
 *
 */
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = -1454588032037092952L;

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
