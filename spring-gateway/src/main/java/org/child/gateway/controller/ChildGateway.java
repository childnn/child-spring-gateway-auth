package org.child.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
@RestController
public class ChildGateway {

    public static void main(String[] args) {
        // String s = new String(Base64.getDecoder().decode("HC7+wTzL5pTGnIakps5uHwRmBTpZ6LxzdvG6oa0wEyM=".getBytes()));
        // System.out.println(s);
    }

    @GetMapping("g")
    Object g() {
        return "hello gateway";
    }

}
