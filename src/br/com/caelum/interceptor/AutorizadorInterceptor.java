package br.com.caelum.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	
	public boolean preHandler(HttpServletRequest request,
			HttpServletResponse response, Object object) throws IOException {
		String uri = request.getRequestURI();
		if(uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.contains("resources")){
			return true;
		}
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		} else {
			response.sendRedirect("loginForm");
			return false;

		}
	}
}
