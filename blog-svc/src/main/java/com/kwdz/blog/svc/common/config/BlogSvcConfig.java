package com.kwdz.blog.svc.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwdz.blog.api.common.util.FastDateFormat;
import com.kwdz.blog.svc.common.dao.SimpleJpaRepository2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共配置 BLOG-SVC
 */

@Slf4j
@ComponentScan({"com.kwdz.*"})
@EnableFeignClients({"com.kwdz.*"})
@EnableJpaRepositories(basePackages = {"com.kwdz.*"}, repositoryBaseClass = SimpleJpaRepository2.class)
@EnableScheduling
@RestController
@Configuration
public class BlogSvcConfig {

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String index() {
        return "BLOG-SVC：启动成功！";
    }

    /**
     * JSON消息处理器
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        FastDateFormat format = FastDateFormat.get();
        objectMapper.setDateFormat(format);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        jsonConverter.setSupportedMediaTypes(list);
        return jsonConverter;
    }

    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> log.info("\n启动成功：" + env.getProperty("blog-svc.url"));
    }

}
