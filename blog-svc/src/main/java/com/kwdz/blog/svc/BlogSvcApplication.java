package com.kwdz.blog.svc;

import com.kwdz.blog.svc.common.config.BlogSvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * BLOG-SVC 启动类
 *
 * @see BlogSvcConfig 公用配置
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BlogSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSvcApplication.class, args);
    }

}
