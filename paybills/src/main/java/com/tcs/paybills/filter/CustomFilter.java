package com.tcs.paybills.filter;

import com.tcs.paybills.controller.PayBillsController;
import com.tcs.paybills.exception.InvalidAuthTokenException;
import com.tcs.paybills.model.Biller;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter()

public class CustomFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(PayBillsController.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authToken = httpRequest.getHeader("authToken");

        logger.info("authToken : " +authToken);
        // Validate the authToken header is not null
        if (authToken == null || authToken.isEmpty()) {
            logger.error("NULL authToken/ Missing authToken : ");
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "authToken header is missing");
            return;
        }

        if(authToken.trim().equalsIgnoreCase(Biller.getAuthToken())){
            logger.info("authToken is VAILD");
            chain.doFilter(request, response);
        } else {
            logger.info("authToken is IN-VAILD");
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "authToken header is INVALID");
            throw new InvalidAuthTokenException();
        }
    }
}
