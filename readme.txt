微服务网关Zuul和Gateway的区别
 - zuul
    spring-cloud-starter-netflix-zuul
    使用的是阻塞式的 API，不支持长连接，比如 websockets。
    底层是servlet，Zuul处理的是http请求
    没有提供异步支持，流控等均由hystrix支持。
    依赖包 spring-cloud-starter-netflix-zuul。
 - Gateway
    spring-cloud-starter-gateway
    gateway对比zuul多依赖了spring-webflux，内部实现了限流、负载均衡等，扩展性也更强，但同时也限制了仅适合于Spring Cloud套件。
    zuul则可以扩展至其他微服务框架中，其内部没有实现限流、负载均衡等。
    Spring Boot和Spring Webflux提供的Netty底层环境，不能和传统的Servlet容器一起使用，也不能打包成一个WAR包。
    依赖 spring-boot-starter-webflux 和 spring-cloud-starter-gateway
    提供了异步支持，提供了抽象负载均衡，提供了抽象流控，并默认实现了RedisRateLimiter。
其他
  gateway线程开销少，支持各种长连接、websocket，spring官方支持，但运维复杂，
  zuul编程模型简单,开发调试运维简单，有线程数限制，延迟堵塞会耗尽线程连接资源。
WebFlux 模块的名称是 spring-webflux，名称中的 Flux 来源于 Reactor 中的类 Flux。Spring webflux 有一个全新的非堵塞的函数式 Reactive Web 框架，可以用来构建异步的、非堵塞的、事件驱动的服务，在伸缩性方面表现非常好。使用非阻塞API。 Websockets得到支持，并且由于它与Spring紧密集成，所以将会是一个更好的 开发 体验。
　　Zuul 1.x，是一个基于阻塞io的API Gateway。Zuul已经发布了Zuul 2.x，基于Netty，也是非阻塞的，支持长连接，但Spring Cloud暂时还没有整合计划。

webflux 支持两种开发模式
    （1）类似于Spring WebMVC的基于注解（@Controller、@RequestMapping）的开发模式；
    （2）Java 8 lambda风格的函数式开发模式。
WebFlux是基于响应式流的，可以用来建立异步、非阻塞、事件驱动的服务。默认采用 Reactor 作为响应式流的实现库，也提供对 RxJava 的支持

相同点:
    1、底层都是servlet
    2、两者均是web网关，处理的是http请求


================================================================================================

Reactor 与 RxJava


spring-boot-2.6.x
禁止循环依赖: 比如 service 自己调用自己的事务问题处理方式, 默认不再可以用自己注入自己
    Relying upon circular references is discouraged and they are prohibited by default.
    Update your application to remove the dependency cycle between beans.
    As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.



spring-security 基于 servlet-filter 实现拦截
spring-security-oauth2 基于 spring-security 实现

