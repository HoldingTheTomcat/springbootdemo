package com.ling.common.entity;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回数据
     */
    protected T data;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 默认为成功
     */
    public Result() {
        this.code = RespCode.SUCCESS.getCode();
        this.msg = RespCode.SUCCESS.getMsg();
    }

    public Result(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public Result<T> setCode(String code) {
        this.code = code;

        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static <T> Result<T> toSuccessInfo(String msg) {
        return new Result<T>().setCode(RespCode.SUCCESS.getCode()).setMsg(msg);
    }

    public static <T> Result<T> toSuccess() {
        return new Result<T>().setCode(RespCode.SUCCESS.getCode()).setMsg(RespCode.SUCCESS.getMsg());
    }

    public static <T> Result<T> toSuccess(T data) {
        Result<T> ResultDto = toSuccess();
        return ResultDto.setData(data);
    }

    public static <T> Result<T> toError(T data) {
        Result<T> ResultDto = toError();
        return ResultDto.setData(data);
    }

    public static <T> Result<T> toError(String errMsg) {
        return new Result<T>().setCode(RespCode.FAILURE.getCode()).setMsg(errMsg);
    }

    public static <T> Result<T> toError() {
        return new Result<T>().setCode(RespCode.FAILURE.getCode()).setMsg(RespCode.FAILURE.getMsg());
    }

    public static <T> Result<T> toErrorInfo(String code) {
        return new Result<T>().setCode(code).setMsg(RespCode.makeFromCode(code).getMsg());
    }
    
    public static <T> Result<T> toErrorInfo(String code,String msg) {
        return new Result<T>().setCode(code).setMsg(msg);
    }

    public static Result<Object> successWithData(Object data) {
        Result<Object> result = new Result<Object>();
        result.setData(data);
        return result;
    }
}
