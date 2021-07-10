package com.liuwq.hdfs.manage.config;

import com.liuwq.hdfs.manage.pojo.Hdfs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Value("${hadoop.uri}")
    private String uri;
    @Bean
    public Hdfs hdfs() throws Exception{

        return new Hdfs(uri);
    }

}
