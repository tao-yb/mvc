package com.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = {"com.mvc"},includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.mvc.interceptor\\..*" ),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.mvc.config\\..*","com.mvc.interceptor" +
                "\\..*"})
},useDefaultFilters = false)
//@EnableWebMvc
public class MvcContainer {
}
