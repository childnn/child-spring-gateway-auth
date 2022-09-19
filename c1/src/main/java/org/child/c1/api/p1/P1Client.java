package org.child.c1.api.p1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
// contextId
// name/value: The name of the service with optional protocol prefix.
@FeignClient(contextId = "p1.P1Client", name = "p1")
public interface P1Client {

    // spring-cloud-openfeign-2.2.2
    // FeignClientSpecification-bean-name 的规则按优先级, 加上 .FeignClientSpecification 后缀
    // -. org.springframework.cloud.openfeign.FeignClient.contextId
    // -. org.springframework.cloud.openfeign.FeignClient.value
    // -. org.springframework.cloud.openfeign.FeignClient.name
    // -. org.springframework.cloud.openfeign.FeignClient.serviceId


    // Ribbon、Feign和OpenFeign的区别
    // 三者区别
    //
    // Ribbon
    // Ribbon 是 Netflix开源的基于HTTP和TCP等协议负载均衡组件
    //
    // Ribbon 可以用来做客户端负载均衡，调用注册中心的服务
    //
    // Ribbon的使用需要代码里手动调用目标服务，请参考官方示例：https://github.com/Netflix/ribbon
    //
    // Feign
    // Feign是Spring Cloud组件中的一个轻量级RESTful的HTTP服务客户端
    //
    // Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。
    //
    // Feign的使用方式是：使用Feign的注解定义接口，调用这个接口，就可以调用服务注册中心的服务
    //
    // Feign支持的注解和用法请参考官方文档：https://github.com/OpenFeign/feign
    //
    // Feign本身不支持Spring MVC的注解，它有一套自己的注解
    //
    // OpenFeign
    // OpenFeign是Spring Cloud 在Feign的基础上支持了Spring MVC的注解，如@RequesMapping等等。
    //
    // OpenFeign的@FeignClient可以解析SpringMVC的@RequestMapping注解下的接口，
    //
    // 并通过动态代理的方式产生实现类，实现类中做负载均衡并调用其他服务。
    //
    // 需要注意，@RequesMapping不能在类名上与@FeignClient同时使用

    @GetMapping("/test")
    Object test();

}
