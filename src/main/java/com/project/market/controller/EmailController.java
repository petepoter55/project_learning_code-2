package com.project.market.controller;


import com.project.market.config.email.MailBuilder;
import com.project.market.dto.req.email.EmailDtoRequest;
import com.project.market.impl.service.email.EmailImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/email")
public class EmailController {
    private static final Logger logger = Logger.getLogger(EmailController.class);

    @Autowired
    private EmailImpl emailImpl;

    @ApiOperation(value = "send email", nickname = "sendEmails", notes = "send email")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String sendEmail(
            @RequestBody(required = true) EmailDtoRequest emailDtoRequest,
            HttpServletRequest request
    ) {
        final EmailDtoRequest mail = new MailBuilder()
                .From("boonyaris.pen@gmail.com")
                .To(emailDtoRequest.getMailTo())
                .Template("email-template/register-success.html")
                .AddContext("subject", emailDtoRequest.getMailSubject())
                .AddContext("content", emailDtoRequest.getMailContent())
                .Subject("Thank For Register!")
                .createMail();
        String responseMessage = request.getRequestURI();
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        try {
            this.emailImpl.sendEmail(mail);
        } catch (Exception e) {
            responseMessage = "Request Unsuccessful " + e.getMessage() + " " + responseMessage;
            return responseMessage;
        }
        responseMessage = "Request Successful " + responseMessage;
        return responseMessage;
    }
}
