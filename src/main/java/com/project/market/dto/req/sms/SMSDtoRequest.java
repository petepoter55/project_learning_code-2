package com.project.market.dto.req.sms;

import lombok.Data;

@Data
public class SMSDtoRequest {
    private String to;
    private String from = "+17816787865";
    private String message;

    @Override
    public String toString() {
        return "SMSDtoRequest{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
