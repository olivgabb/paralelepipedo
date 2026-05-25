package com.api.paralelepipedo.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.paralelepipedo.repositories.UserRepository;
import com.api.paralelepipedo.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class TokenFilter extends OncePerRequestFilter {

	
	@Autowired
	JWTService jwtService;
	@Autowired
	UserRepository userRepo;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = this.getToken(request);
		//System.out.println(request.getHeader("Authorization"));
		String path = request.getServletPath();

		if(path.equals("/auth/login")) {
		    filterChain.doFilter(request, response);
		    return;
		}
		
		if(token!=null)
		{
			var login= jwtService.validateToken(token);
			System.out.println("\n\n\n\nThis is my login: " +login);
			if (login != null) {
				if (userRepo.findByEmail(login).isPresent()) {
					UserDetails user = userRepo.findByEmail(login).get();
					//System.out.println(user.getAuthorities());
					var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest req)
	{
		var token = req.getHeader("Authorization");
		
		if(token==null) return null;
		
		return token.replace("Bearer ", "");
	}

}
