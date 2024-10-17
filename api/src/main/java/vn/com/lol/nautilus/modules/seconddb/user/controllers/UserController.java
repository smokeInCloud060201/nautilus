package vn.com.lol.nautilus.modules.seconddb.user.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.nautilus.commons.controller.BaseController;

import static vn.com.lol.nautilus.commons.constant.ControllerPathConstant.USER_BASE;

@RestController
@RequestMapping("/api/v1" + USER_BASE)
public class UserController extends BaseController {

    @GetMapping("/hello")
    public void hello(Authentication authentication) {
        System.out.println(authentication);
        System.out.println("!23123");
    }
}
