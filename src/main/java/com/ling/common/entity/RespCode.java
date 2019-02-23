package com.ling.common.entity;

public enum RespCode {

	/**
	 * 系统通用返回码
	 */
	SUCCESS("0000", "操作成功"),
	FAILURE("9999","未知异常，请联系管理员"),

	/**
	 * 文件上传相关错误码
	 */
	UPLOAD_FILE_ERROR("0010","上传文件异常"),
	UPLOAD_FILE_TYPE_ERROR("0011","文件格式不符合"),
	UPLOAD_FILE_SIZE_ERROR("0012","文件过大，无法上传"),
	UPLOAD_FILE_IS_NULL_ERROR("0013","文件解析为null，请重新上传"),
	UPLOAD_FILE_IS_EMPTY_ERROR("0014","文件解析为空，请重新上传"),
	UPLOAD_FILE_CLIENTID_ERROR("0015","文件服务clientID异常，无法上传"),
	UPLOAD_FILE_BUCKET_ERROR("0016","文件服务bucket异常，无法上传"),
	/**
	 * 文件上传相关错误码
	 */
	DOWNLOAD_FILE_ERROR("0020","文件下载异常"),
	DOWNLOAD_FILE_NOT_FIND_PATH("0021","文件不存在，无法下载"),
	DOWNLOAD_FILE_PARSE("0022","解析文件失败"),

	/**
	 * 还款计划相关错误码
	 */
    QUERY_AFTER_LOAN_ORDER_DETAIL_ERROR("1010","查询订单详情为null"),
    NOT_EXIST_ORDER_INFO_ERROR("1031","未存在对应订单"),
    NOT_EXIST_CONTRACT_INFO_ERROR("1032","未存在对应合同"),
    CHANGE_REPAY_TIME_ERROR("1033","变更时间不在合同有效期内"),
	CREATE_REPAY_PLAY_ERROR("1020","推送还款计划失败"),
    NOT_EXIST_REAPY_PLAN_ERROR("1021","未存在对应的还款计划"),
    NOT_FINISH_LOAN_STATUS_ERROR("1022","\'非还款中（正常）/还款中（提前）/推送单次还款计划失败\'不能推送还款计划"),
    NOT_SEND_REPAY_PLAN_ERROR("1023","状态在'推送单次还款计划校验中'才能查询结果"),
    QUERY_ONE_REPAY_PLAN_ERROR("1024","查询单次推送结果失败"),
    NOT_QUERY_ONE_REPAY_PLAN_ERROR("1025","未查询到推送单次还款计划的结果"),
    SEND_AFTER_LOAN_MATERIAL_ERROR("1026","推送贷后材料失败"),
    MUST_SEND_AFTER_LOAN_MATERIAL("1027","需先上传贷后材料"),
    SEND_AFTER_MATERIAL_ERROR("1028","推送贷后材料不足"),
    IS_EXIST_PAY_OFF_INFO("1030","已存在还款记录，不能再此操作"),
    NOT_EXIST_PAY_OFF_INFO("1031","未提交还款流水号，不能查询核销结果"),
    PAY_OFF_YET("1032","订单已结清，不能重复查询"),

	/**
     * 资金方，通道错误码
     */
	NAME_IS_EXIT("1011","名称已存在"),
	CHANNEL_ADD_FAIL("1012","新增失败"),
	CHANNEL_NOT_FOUND("1013","查询详情失败"),
	

	/**
	 * 创建订单错误码
	 */
	ORDER_NOT_FOUND("2001", "该订单不是赎楼贷产品或不是待面签订单"), PRODUCT_NOT_FOUND("2002", "查询产品信息失败"),

    SEND_ORDER_FAIL("2003", "推送订单失败"),
    FILE_NOT_FOUND("2004", "推送订单成功,需要上传的文件为空"),
    SEND_FILE_FAIL("2005", "推送订单成功,文件推送失败"),
    LOAN_MAN_NOT_FOUND("2006", "借款人查找失败"),
    LOAN_MONEY_NOT_FOUND("2007", "申请贷款获取失败"),
    FIANCE_MAN_NOT_FOUND("2008", "金融顾问获取失败"),
    PARAM_IS_REQUIRED("2009", "订单编号为必传字段"),
    ORDER_CITY_NOT_FOUND("2010", "订单所属城市获取失败"), PEND_ORDER_NOT_FOUND("2011", "待推送的订单不存在或订单状态错误"),
    ORDER_STATUS_ERROR("2012", "订单状态错误"),
    ORDER_STATUS_IS_PAY_OFF("2013", "订单已结清"),
    ORDER_NOT_IN_RANGE_TIME("2018", "超出已选产品期限"),
    
    /**
     * 产品信息错误码
     */
    PRODUCT_INFO_NOT_FOUND("2014","产品信息获取失败"),
    PRODUCT_CHANGE_FAIL("2015","产品信息修改失败"),
    PRODUCT_ADD_FAIL("2016","产品信息新增失败"),
    PRODUCT_PST_EXIT("2017","产品名称或产品编号或产品资金方，通道，期数已存在"),


	/**
	 *  合同与放款错误码
	 */
    GETRQCODE_FAIL("3000", "获取二维码失败"),
    GETCONTRACTSTATUS_FAIL("3001", "获取合同签约状态失败"),
    GETCONTRACTFILE_FAIL("3002", "获取合同文件失败"),
    LOAN_FAIL("3003", "放款失败"),
    loanCancel_FAIL("3004","取消贷款失败"),
    LOAN_DOING("3005","重复放款");

    /**
     * @Fields code : 返回码
     */
    private String code;

    /**
     * @Fields msg :信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    RespCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RespCode makeFromCode(String errorCode) {
        for (RespCode respCode : RespCode.values()) {
            if (respCode.getCode().equals(errorCode)) {
                return respCode;
            }
        }
        throw new RuntimeException("非法的错误编码: " + errorCode);
    }
}
