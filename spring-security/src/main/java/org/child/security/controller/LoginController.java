package org.child.security.controller;

import org.child.security.entity.ResponseResult;
import org.child.security.entity.User;
import org.child.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/09
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    ResponseResult<String> login(@RequestBody User user) {

        return new ResponseResult<>(1, "", loginService.login(user));
    }

}
