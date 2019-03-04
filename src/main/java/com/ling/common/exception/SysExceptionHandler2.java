package com.ling.common.exception;

import com.ling.common.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.List;


// 全局异常拦截器，先拦截业务异常，再拦截系统异常
// @RestControllerAdvice
public class SysExceptionHandler2 {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// 拦截自定义业务异常
	@ExceptionHandler(BusinessException.class)
	public Result handleBusinessException(BusinessException businessException) {
		//返回异常code和业务异常信息
	    Result r = new Result(businessException.getCode(), businessException.getMessage());
		//打印异常日志
		logger.error("业务异常 - "+businessException.getMessage()+"：状态码( "+ businessException.getCode()+" )", businessException);
		return r;
	}

	//javax.validation.Valid校验拦截
	@ExceptionHandler(value = BindException.class)
	public Result bindExceptionErrorHandler(BindException ex) throws Exception {
		StringBuilder sb = new StringBuilder("请求参数错误; ");
		List<FieldError> fieldErrors = ex.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String errorMessage = String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage());
			sb.append(errorMessage);
			sb.append(";");
		}
		logger.error(sb.toString());
		return Result.toError(sb.toString());
	}

	//拦截系统异常并跳转到错误页面
	@ExceptionHandler(Exception.class)
	public ModelAndView handleExceptionToErrorHtml(Exception e) {
		ModelAndView mv = new ModelAndView();
		//打印异常日志
		logger.error("系统异常：" + e.getMessage(), e);
		mv.setViewName("error");
		if (e.getClass() == SQLInvalidAuthorizationSpecException.class) {
			mv.addObject("exceptionMessage", "数据库中已存在该记录");
		}
		if (e.getClass() == DuplicateKeyException.class) {
			mv.addObject("exceptionMessage", "没有权限，请联系管理员授权");
		}
		return mv;
	}


}
