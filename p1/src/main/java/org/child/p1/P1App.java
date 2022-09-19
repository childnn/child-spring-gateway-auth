package org.child.p1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
@EnableDiscoveryClient
@SpringBootApplication
public class P1App {

    public static void main(String[] args) {
        SpringApplication.run(P1App.class, args);
    }

}
