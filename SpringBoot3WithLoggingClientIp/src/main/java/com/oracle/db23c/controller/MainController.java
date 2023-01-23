package com.oracle.db23c.controller;

import com.google.common.base.Strings;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@Slf4j
public class MainController {

    @GetMapping("/hello")
    public String sayHello(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        log.info("getRemoteAddr() is: " + remoteAddr);

        String xIp = request.getHeader("X-Real-IP");
        log.info("X-Real-IP is: " + xIp);

        String xFor = request.getHeader("X-Forwarded-For");
        if (!Strings.isNullOrEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                xFor = xFor.substring(0, index);
            }
        }
        log.info("X-Forwarded-For is: " + xFor);

        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
            log.info("Proxy-Client-IP is: " + xFor);
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP is: " + xFor);
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
            log.info("HTTP_CLIENT_IP is: " + xFor);
        }
        if (Strings.nullToEmpty(xFor).trim().isEmpty() || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("HTTP_X_FORWARDED_FOR is: " + xFor);
        }

        return "RemoteAddress: [" + remoteAddr + "], X-Real-IP: [" + xIp + "], X-Forwarded-For: [" + xFor + "]";
    }
}
