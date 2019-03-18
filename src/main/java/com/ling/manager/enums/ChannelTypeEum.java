package com.ling.manager.enums;

/**
 * Created by TianHeLing on 2018/9/6.
 */
public enum ChannelTypeEum {

    YxChannel(0, "云信"),
    WmChannel(1, "外贸");

    /**
     * @Fields code : 返回码
     */
    private int code;

    /**
     * @Fields msg :信息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ChannelTypeEum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
