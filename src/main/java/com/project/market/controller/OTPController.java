package com.project.market.controller;

import com.project.market.dto.req.sms.SMSDtoRequest;
import com.project.market.impl.service.otp.OtpImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sms")
public class OTPController {
    private static final Logger logger = Logger.getLogger(OTPController.class);

    private final OtpImpl otpImpl;

    @Autowired
    public OTPController(OtpImpl otpImpl) {
        this.otpImpl = otpImpl;
    }

    @ApiOperation(value = "send sms", nickname = "sendSMS", notes = "send sms")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sendSMS(
            @RequestBody(required = true) SMSDtoRequest smsDtoRequest,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.otpImpl.sendSMS(smsDtoRequest);
    }
}
