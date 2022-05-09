package com.project.market.model;

import com.project.market.constant.Constant;
import com.project.market.impl.exception.ResponseException;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TemplateKeeper {
    private static final Logger logger = Logger.getLogger(TemplateKeeper.class);
    private Map<String, String> requests;

    public TemplateKeeper() {
        requests = new ConcurrentHashMap<>();
    }

    public Map<String, String> getRequests() {
        return requests;
    }

    public void setRequests(Map<String, String> requests) {
        this.requests = requests;
    }

    public String getTemplate(String requestName, String pathSchemaRequest) throws ResponseException {
        if (!requests.containsKey(pathSchemaRequest)) {
            logger.info("Template name: {} for service: {} not found, reading a new template : " + requestName +" : "+ pathSchemaRequest);
            try{
                InputStream template = new ClassPathResource(pathSchemaRequest).getInputStream();
                String templateString = new String(FileCopyUtils.copyToByteArray(template), StandardCharsets.UTF_8);
                requests.put(requestName, templateString);
                return templateString;
            } catch (IOException e) {
                logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
            }
        }
        return requests.get(requestName);
    }
}
