package com.ling.common.exception;

import com.ling.common.entity.RespCode;

/**
 * 业务异常对象
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;

	public BusinessException() {
		super();
	}

	/**
	 * 通过错误信息创建业务异常对象
	 * 
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * 通过错误信息，错误码创建业务异常对象
	 * 
	 */
	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public BusinessException(RespCode respCode) {
		super(respCode.getMsg());
		this.code = respCode.getCode();
	}
	
	/**
	 * 通过错误信息，异常对象，错误码创建业务异常对象
	 * 
	 */
	public BusinessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * 通过异常对象，错误码创建业务异常对象
	 * 
	 */
	public BusinessException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

}
