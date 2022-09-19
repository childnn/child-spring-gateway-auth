package org.child.c1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
@SpringBootApplication
@EnableFeignClients
public class C1App {

    public static void main(String[] args) {
        SpringApplication.run(C1App.class, args);
    }

}
