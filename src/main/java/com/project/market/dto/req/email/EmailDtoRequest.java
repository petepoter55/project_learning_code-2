package com.project.market.dto.req.email;

import lombok.Data;

@Data
public class EmailDtoRequest {
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String templateName;
    private String contentType = "text/html";

    @Override
    public String toString() {
        return "EmailDtoRequest{" +
                "mailFrom='" + mailFrom + '\'' +
                ", mailTo='" + mailTo + '\'' +
                ", mailCc='" + mailCc + '\'' +
                ", mailBcc='" + mailBcc + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", templateName='" + templateName + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
