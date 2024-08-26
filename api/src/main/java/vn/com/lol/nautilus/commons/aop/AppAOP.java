package vn.com.lol.nautilus.commons.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import vn.com.lol.common.aop.AppGlobalLogger;

@Component
public class AppAOP extends AppGlobalLogger {
    public AppAOP(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }
}
