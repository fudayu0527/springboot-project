package com.ksl.common.core.domain;

import com.ksl.common.core.constant.HttpStatus;
import lombok.Getter;

/**
 * 操作消息提醒
 *
 * @author ksl
 */
@Getter
public class Result<T> {

    public static final String SUCCEED_MSG = "操作成功";


    private int code;

    private String msg;

    private T data;

    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> Result<T> success() {
        return Result.success("操作成功", null);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> Result<T> success(T data) {
        return Result.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Result<Void> successMsg(String msg) {
        return Result.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<T>(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> Result<Void> error() {
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> Result<T> error(String msg) {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }
}
