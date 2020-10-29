package com.action.trends.util;

import java.util.Date;

import com.action.trends.dto.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

public class JWTUtil {
	static final String tokenSecret = "HS256"; // 암호화 방식
	
	/* 회원 정보 JWT 암호화 */
	public static String createJWTToken(String email) {
		String token = null;

		try {
			// 토큰 유효 기간
			Long EXPIRATION_TIME = 1000L * 60L * 60L * 24L * 2L;
			Date issuedAt = new Date();
			Date notBefore = new Date(issuedAt.getTime());
			Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

			// 토큰 암호화
			Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
			token = JWT.create().withIssuer("auth0").withAudience("TrendsAction")
					.withClaim("email", email).withNotBefore(notBefore).withExpiresAt(expiresAt).sign(algorithm);
		} catch (Exception e) { System.err.println("err: " + e); }
		return token;
	}
	
	/* JWT 검증 */
	public static String verifyToken(String token) {
		if (token == null) return "error";
		try {
			String result = JWT.require(Algorithm.HMAC256(tokenSecret.getBytes())).build()
					.verify(token.replaceFirst("Bearer", "").replaceFirst(" ", "")).getClaim("email").asString();
			return result;
		} catch (Exception e) { return "error"; }
	}
}
