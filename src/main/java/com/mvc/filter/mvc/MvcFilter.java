package com.mvc.filter.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MvcFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(MvcFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Long start = System.currentTimeMillis();
        System.out.println("MvcFilter doFilterInternal.");
        StringBuilder sb = new StringBuilder();
        sb.append(getRemoteIp(request)).append(" ");
        sb.append(request.getMethod()).append(" ");
        appendRequestURL(sb, request);
        sb.append(response.getStatus()).append(" ");
        sb.append(System.currentTimeMillis() - start).append(" ");
        appendRequestPayload(sb, request);

        logger.info(sb.toString());


        filterChain.doFilter(request,response);
    }

    private void appendRequestPayload(StringBuilder sb, HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buffer = wrapper.getContentAsByteArray();
            if (buffer.length > 0) {
                String payload;
                try {
                    int length = Math.min(buffer.length, 4096);
                    payload = new String(buffer, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }

                payload = payload.replaceAll("\\r|\\n", "");
                sb.append("payload: ").append(payload);
            }
        }
    }

    private String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private void appendRequestURL(StringBuilder sb, HttpServletRequest request) {
        sb.append(request.getRequestURI());

        String queryString = request.getQueryString();
        if (queryString != null) {
            sb.append("?").append(queryString);
        }

        sb.append(" ");
    }


}
