package com.slalom.demo.app;

import com.slalom.demo.ipChecker.model.IpInfo;
import com.slalom.demo.ipChecker.service.IpCheckerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication(scanBasePackages = "com.slalom.demo")
@RestController
public class DemoApplication {

    private Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    private final IpCheckerClient ipCheckerClient;

    public DemoApplication(IpCheckerClient ipCheckerClient) {

        this.ipCheckerClient = ipCheckerClient;
    }

    @GetMapping("/")
    public String ip(HttpServletRequest request) {
        IpInfo a = ipCheckerClient.getInfo("1.1.1.1");
        logger.info("Remote IP: {}", request.getRemoteAddr());
        return a.getIsp();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}