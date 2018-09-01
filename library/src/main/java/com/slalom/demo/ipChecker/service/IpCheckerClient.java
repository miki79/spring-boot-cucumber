package com.slalom.demo.ipChecker.service;

import com.slalom.demo.ipChecker.model.IpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;


@Service
@EnableConfigurationProperties(IpCheckerProperties.class)
public class IpCheckerClient {

    private final IpCheckerProperties ipCheckerProperties;

    public IpCheckerClient(IpCheckerProperties ipCheckerProperties) {
        this.ipCheckerProperties = ipCheckerProperties;
    }

    public IpInfo getInfo(String ip) {

        return ipCheckerProperties.getRestTemplate().getForObject(ipCheckerProperties.getHostname() + "/json/{ip}", IpInfo.class, ip);
    }

}

