package com.slalom.demo.ipChecker.service;

import com.slalom.demo.ipChecker.service.stub.RestTemplateStub;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.client.RestTemplate;

@ConfigurationProperties("ip-checker")
public class IpCheckerProperties {

    private String hostname = "http://ip-api.com";
    private Boolean stubbed = false;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Boolean getStubbed() {
        return stubbed;
    }

    public void setStubbed(Boolean stubbed) {
        this.stubbed = stubbed;
    }

    public RestTemplate getRestTemplate() {
        if (this.stubbed) {
            return new RestTemplateStub();
        }
        return new RestTemplate();
    }
}
