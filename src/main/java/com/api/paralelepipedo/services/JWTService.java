package com.api.paralelepipedo.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.paralelepipedo.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	public String generateToken(User user)
	{
		try {
			Algorithm alg = Algorithm.HMAC256(secret); 
			String jwt = JWT.create()
					.withIssuer("auth-api")
					.withSubject(user.getEmail())
					.withClaim("role", user.role.getRole())
					.withExpiresAt(genExpireTime())
					.sign(alg);
			return jwt;
		}catch(JWTCreationException e) {
			throw new RuntimeException("Could not generate JWT token", e);
		}
		
		
	}
	
	public String validateToken(String token)
	{
		
		try {
			Algorithm alg = Algorithm.HMAC256(secret); 
			String jwt = JWT.require(alg)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
					
			return jwt;
		}catch(JWTVerificationException e) {
			return "";
		}
		
		
	}
	
	
	
	private Instant genExpireTime()
	{
		//return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
		return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
	}
}
