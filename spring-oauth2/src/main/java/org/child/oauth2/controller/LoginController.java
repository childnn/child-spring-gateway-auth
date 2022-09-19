package org.child.oauth2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 */
@RestController
public class LoginController {

    @PostMapping("login")
    Object login() {
        return "login";
    }

}
