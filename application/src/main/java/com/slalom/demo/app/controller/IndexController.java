package com.slalom.demo.app.controller;

import com.slalom.demo.app.DemoApplication;
import com.slalom.demo.ipChecker.model.IpInfo;
import com.slalom.demo.ipChecker.service.IpCheckerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    private final IpCheckerClient ipCheckerClient;

    public IndexController(IpCheckerClient ipCheckerClient) {

        this.ipCheckerClient = ipCheckerClient;
    }

    @GetMapping("/")
    public String ip(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        IpInfo a = ipCheckerClient.getInfo(ipAddress);
        logger.info("Remote IP: {}", ipAddress);
        return a.getIsp();
    }
}
