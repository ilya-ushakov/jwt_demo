package com.jooq.proto.jwtangspr;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class ApiController {
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "role/{role}", method = RequestMethod.GET)
	public Boolean login(@PathVariable final String role,
			final HttpServletRequest request) throws ServletException { 
		final Claims claims = (Claims) request.getAttribute("claims");
		System.out.println(String.format("ApiController login uri: %s", request.getRequestURI()));
		return ((List<String>) claims.get("roles")).contains(role);
	}
}
