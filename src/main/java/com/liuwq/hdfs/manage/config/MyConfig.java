package com.liuwq.hdfs.manage.config;

import com.liuwq.hdfs.manage.intercepter.HdfsIntercepter;
import com.liuwq.hdfs.manage.pojo.Hdfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport{
    @Value("${hadoop.uri}")
    private String uri;
    @Autowired
    private HdfsIntercepter hdfsIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(hdfsIntercepter).addPathPatterns("/**");
    }
    @Bean
    public Hdfs hdfs() throws Exception{
        return new Hdfs(uri);
    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowCredentials(true);
//        corsConfig.addAllowedOriginPattern(CorsConfiguration.ALL);
//        corsConfig.addAllowedMethod(CorsConfiguration.ALL);
//        corsConfig.addAllowedHeader(CorsConfiguration.ALL);
//        //默认可不设置这个暴露的头。这个为了安全问题，不能使用*。设置成*，后面会报错：throw new IllegalArgumentException("'*' is not a valid exposed header value");
//        //corsConfig.addExposedHeader("");
//        corsConfig.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", corsConfig);
//
//        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(configSource));
//        corsBean.setName("crossOriginFilter");
//        corsBean.setOrder(0);//这个顺序也有可能会有影响，尽量设置在拦截器前面
//        return corsBean;
//    }

}
