package com.action.trends.filter;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.action.trends.util.JWTUtil;
import com.google.common.net.HttpHeaders;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreFilter extends ZuulFilter {

	@Autowired
	JWTUtil jwtUtil;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		System.out.println("===== 필터링 메소드 =====");
		System.out.println("Request Method : " + request.getMethod());
		System.out.println("Request URL : " + request.getRequestURI().toString());

		return needAuth(request.getRequestURI());
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();

		String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
		System.out.println("Request Authentication : " + authentication);

		String email = request.getHeader("Email");
		System.out.println("Request Email : " + email);

		// 인증 실패
		if (!jwtUtil.verifyToken(authentication).equals(email)) {
			System.out.println("Fail to Authentication");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			ctx.setResponseBody("{errorCode: 401, errorMessage:\"Unauthorized\"}");
		}

		return null;
	}

	private boolean needAuth(String requestURI) {
		// 스웨거, 트렌드 API, 닉네임 중복 => 인증 필요 x
		if (requestURI.contains("swagger") || requestURI.contains("api-docs") || requestURI.contains("trend/")
				|| requestURI.contains("/user/checknickname/")) {
			System.out.println("인증 필요 X");
			return false;
		}

		System.out.println("인증 필요 O");
		return true;
	}

}
