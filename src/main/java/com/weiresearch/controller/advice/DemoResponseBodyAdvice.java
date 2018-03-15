package com.weiresearch.controller.advice;


import com.weiresearch.exception.ServiceLoginExpiredException;
import com.weiresearch.pojoentity.Response;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DemoResponseBodyAdvice implements ResponseBodyAdvice {

	// 设置需要进行后置处理的接口
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {

		// 任何接口返回值均需要处理
		return true;
	}

	// 接口后置处理，这里用于统一个格式化响应体
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

		// 抛出异常的接口响应已在下面的全局异常处理方法中进行过格式化，可直接返回
		// 下载文件接口无需格式化，可直接返回
		if (body != null && (body.getClass() == Response.class || body.getClass() == UrlResource.class)) {
			return body;
		}

		return new Response(Response.CODE_SUCCESS, body);
	}

	// 全局异常处理方法，将根据异常类型对响应体进行特殊包装，设置相应 code
	// 登录超时异常
	@ExceptionHandler(ServiceLoginExpiredException.class)
	@ResponseBody
	public Response handleLoginExpiredException(HttpServletRequest request, Exception ex) {
		return new Response(Response.CODE_LOGIN_EXPIRED, ex.getMessage());
	}

	// 其他运行时异常
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Response handleRuntimeException(HttpServletRequest request, Exception ex) throws Exception {
		if (ex.getClass() == ServiceLoginExpiredException.class) {

			// http://blog.csdn.net/liujia120103/article/details/75126124
			throw ex;
		}

		ex.printStackTrace();

		return new Response(Response.CODE_FAIL, ex.getMessage());
	}

}
