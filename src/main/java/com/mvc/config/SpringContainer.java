package com.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
/*@ComponentScan(value = "com.mvc",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class),
        @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.mvc.interceptor.*" ),
        @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.mvc.interceptor\\.*" ),

})*/

@ComponentScan(basePackages = "com.mvc",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class),
//                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.mvc.config.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.mvc.config\\..*","com.mvc.interceptor" +
                        "\\..*"})
})
public class SpringContainer {
}
