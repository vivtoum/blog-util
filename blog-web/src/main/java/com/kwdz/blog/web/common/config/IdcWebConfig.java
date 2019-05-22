package com.kwdz.blog.web.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共配置 BLOG-WEB
 *
 * @author YT.Hu
 */
@Slf4j
@ComponentScan({"com.kwdz.*"})
@EnableFeignClients({"com.kwdz.*"})
//@EnableRedisHttpSession
@EnableEurekaClient
@Configuration
@RestController
public class IdcWebConfig {

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String index() {
        return "BLOG-WEB：启动成功！";
    }

    /**
     * 返回基础平台URL 注意这里顺序必须最低即最后执行 防止变量丢失
     */
    @Bean
    @Order
    public WebMvcConfigurer addModelAttrInterceptor() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptorAdapter() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                        request.setAttribute("bpmWebUrl", env.getProperty("bpmWebUrl"));
                        return true;
                    }
                });
            }
        };
    }


    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> log.info("\n启动成功：" + env.getProperty("blog-web.url"));
    }

}
