package org.child.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/09
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    Object hello() {
        return "hello, im security";
    }

}
