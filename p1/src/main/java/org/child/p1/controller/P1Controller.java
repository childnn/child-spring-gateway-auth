package org.child.p1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/05
 */
@RestController
public class P1Controller {

    // 注: 此接口在 c1 模块中有远程调用测试, 路径如需修改请注意同步
    @GetMapping("test")
    Object test() {
        return "访问到p1";
    }

}
