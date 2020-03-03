package com.pvthach.foodproducer.security.jwt;

import com.pvthach.foodproducer.message.response.EnumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by THACH-PC
 */

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e)
                        		 throws IOException, ServletException {
    	
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, EnumResponse.UNAUTHORIZED.getDescription());
    }
}