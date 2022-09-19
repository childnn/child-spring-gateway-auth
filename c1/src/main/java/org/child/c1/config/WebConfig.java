package org.child.c1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig {

    /**
     * todo: json 转换 -- feign 调用需要
     * Could not extract response: no suitable HttpMessageConverter found for response type [class java.lang.Object] and content type [text/plain;charset=UTF-8]
     *
     * @see org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
     */
    @Bean
    WebMvcConfigurer webMvcConfigurer(Jackson2ObjectMapperBuilder builder) {
        return new WebMvcConfigurer() {
            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                // ObjectMapper om = new ObjectMapper();
                // om.
                ObjectMapper om = builder.build();
                converters.add(0, mappingJackson2HttpMessageConverter(om));
            }
        };
    }

    // @Bean
    // @ConditionalOnMissingBean(value = MappingJackson2HttpMessageConverter.class,
    //         ignoredType = {
    //                 "org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter",
    //                 "org.springframework.data.rest.webmvc.alps.AlpsJsonHttpMessageConverter" })
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper om) {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(om);
        converter.setSupportedMediaTypes(
                Arrays.asList(MediaType.ALL, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN));
        return converter;
    }

    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.createXmlMapper(false);
            // .su
        };
    }

}
