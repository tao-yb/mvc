package com.mvc.filter.mvc;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class RequestLogFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        System.out.println("before ================》com.mvc.filter.mvc.RequestLogFilter:" + message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        System.out.println("after ******************* com.mvc.filter.mvc.RequestLogFilter:" + message);
    }


}
