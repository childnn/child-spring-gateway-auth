package org.child.c1.controller;

import org.child.c1.api.p1.P1Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
@RestController
public class C1Controller {

    @Autowired
    private P1Client p1Client;


    @GetMapping("c1fromp1test")
    Object c1fromp1test() {
        return p1Client.test();
    }


    @GetMapping("c1test")
    Object c1test() {
        return "访问到 c1";
    }


}
