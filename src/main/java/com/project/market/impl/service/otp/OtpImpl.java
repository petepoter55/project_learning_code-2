package com.project.market.impl.service.otp;

import com.project.market.dto.req.sms.SMSDtoRequest;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.service.jwt.JwtImpl;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import netscape.javascript.JSObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OtpImpl {
    private static final Logger logger = Logger.getLogger(OtpImpl.class);
    @Value("${spring.twilio.account.sid}")
    private String twilioAccountSid;
    @Value("${spring.twilio.auth.token}")
    private String twilioAuthToken;

    public ResponseEntity<String> sendSMS(SMSDtoRequest smsDtoRequest) {
        logger.info("request sms : " + smsDtoRequest.toString());
        Twilio.init(twilioAccountSid, twilioAuthToken);
        try {
            Message.creator(new PhoneNumber(smsDtoRequest.getTo()), new PhoneNumber(smsDtoRequest.getFrom()), smsDtoRequest.getMessage()).create();
        } catch (ResponseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
