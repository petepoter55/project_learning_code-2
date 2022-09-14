package com.project.market.config.email;

import com.project.market.constant.Constant;
import com.project.market.dto.req.email.EmailDtoRequest;
import com.project.market.impl.exception.ResponseException;
import com.project.market.util.VelocityUtil;

import java.util.HashMap;
import java.util.Map;


public class MailBuilder {
    private String subject;
    private String mailTo;
    private String mailFrom;
    private String template;
    private VelocityUtil velocityUtil;
    private Map<String, Object> data;

    public MailBuilder() {
        this.mailTo = "";
        this.mailFrom = "";
        this.subject = "";
        this.template = "";
        this.velocityUtil = new VelocityUtil();
        this.data = new HashMap<>();
    }

    public MailBuilder Subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder To(String to) {
        this.mailTo = to;
        return this;
    }

    public MailBuilder From(String from) {
        this.mailFrom = from;
        return this;
    }

    public MailBuilder Template(String template) {
        this.template = template;
        return this;
    }

    public MailBuilder AddContext(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public MailBuilder AddContext(String key, String value) {
        data.put(key, value);
        return this;
    }

    public EmailDtoRequest createMail() throws IllegalArgumentException {
        final String templateEngine = velocityUtil.fillDataToTemplate("email-template", this.template, this.data);
        if (this.mailTo.isEmpty() || this.mailFrom.isEmpty()) {
            throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_EMAIL_HEADER_FOUND);
        }
        final EmailDtoRequest result = new EmailDtoRequest();
        result.setMailTo(this.mailTo);
        result.setMailFrom(this.mailFrom);
        result.setMailContent(templateEngine);
        result.setMailSubject(this.subject);

        return result;
    }
}
