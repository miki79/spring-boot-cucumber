package com.slalom.demo.ipChecker.service.stub;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

public class RestTemplateStub extends RestTemplate {

    private Logger logger = LoggerFactory.getLogger(RestTemplateStub.class);

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        try {
            String filePath = "valid";
            if (StringUtils.contains((String) uriVariables[0], "1.1.1.1")) {
                filePath = "valid-bt";
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Object response = objectMapper
                    .readValue(new File(getClass().getClassLoader().getResource("stub/" + filePath + ".json").getFile()), responseType);
            return ((T) response);
        } catch (IOException e) {
            logger.error("Invalid stub file", e);
            throw new RestClientException("Invalid stub file");
        }
    }
}
