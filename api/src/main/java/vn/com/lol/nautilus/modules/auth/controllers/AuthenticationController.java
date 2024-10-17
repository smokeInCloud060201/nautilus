package vn.com.lol.nautilus.modules.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.nautilus.commons.controller.BaseController;

import static vn.com.lol.nautilus.commons.constant.ControllerPathConstant.AUTH_BASE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1" + AUTH_BASE)
public class AuthenticationController extends BaseController {

}
