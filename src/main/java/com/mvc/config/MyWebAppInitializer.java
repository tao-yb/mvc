package com.mvc.config;

import com.mvc.filter.CorsFilter;
import com.mvc.filter.MyFilter;
import com.mvc.filter.ServletFilter;
import com.mvc.filter.mvc.MvcFilter;
import com.mvc.filter.mvc.RequestLogFilter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringContainer.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcContainer.class};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("MyWebAppInitializer startup ...");
        super.onStartup(servletContext);

/*        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(MvcContainer.class);
//		ac.refresh();
        // DispatcherServlet注册到web容器
        DispatcherServlet servlet = new DispatcherServlet(ac);// servlet
        ServletRegistration.Dynamic registration = servletContext.addServlet("DispatcherServlet", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");*/


    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new ServletFilter(), new MvcFilter(), new RequestLogFilter(), new MyFilter(), new CorsFilter()};
    }
}
