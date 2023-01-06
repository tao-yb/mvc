package com.mvc;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringApplication {

    public static final String PROJECT_SOURCE = "";

    public static void main(String[] args) throws ServletException, LifecycleException {
        run(SpringApplication.class,args);
    }

/*    private static void run(Class<SpringApplication> springApplicationClass, String[] args) throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        String basePath = System.getProperty("user.dir") + File.separator + PROJECT_SOURCE + File.separator;
        System.out.println("base Path :" + basePath);
        tomcat.getHost().setAppBase(basePath);

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", basePath + "src" + File.separator + "main" + File.separator + "resources");
        ctx.setReloadable(false);

        File file = new File(PROJECT_SOURCE + "/target/classes");
        WebResourceRoot resourceRoot = new StandardRoot(ctx);
        resourceRoot.addPreResources(new DirResourceSet(resourceRoot, PROJECT_SOURCE + "/WEB-INF/classes",
                file.getAbsolutePath(), "/"));
        tomcat.start();
        tomcat.getServer().await();
    }*/

    public static void run(Object source, String... args) {
//        return run(new Object[] { source }, args);

        try {
            // 创建Tomcat容器
            Tomcat tomcatServer = new Tomcat();
            // 端口号设置
            tomcatServer.setPort(8090);
            // 读取项目路径 加载静态资源
//            StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("spring-source/src/main").getAbsolutePath());
            final String sourcePath = "";
            String basePath = System.getProperty("user.dir") + File.separator + sourcePath + File.separator;
            tomcatServer.getHost().setAppBase(basePath);

            //改变文件读取路径，从resources目录下去取文件
            StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", basePath + "src" + File.separator + "main" + File.separator + "resources");
            // 禁止重新载入
            ctx.setReloadable(false);
            // class文件读取地址
            File additionWebInfClasses = new File(sourcePath + "/target/classes");
            // 创建WebRoot
            WebResourceRoot resources = new StandardRoot(ctx);
            // tomcat内部读取Class执行
            resources.addPreResources(
                    new DirResourceSet(resources, sourcePath + "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            tomcatServer.start();
            // 异步等待请求执行
            tomcatServer.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
