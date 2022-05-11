package com.project.market.controller;

import com.project.market.dto.res.Response;
import com.project.market.impl.MarketInformationImpl;
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
@RequestMapping("/information")
public class MarketInformationController {
    private static final Logger logger = Logger.getLogger(MarketInformationController.class);

    @Autowired
    private MarketInformationImpl marketInformationImpl;

//    @Consumes ใช้สำหรับ request และ @Produces ใช้สำหรับ response
    @ApiOperation(value = "register information", notes = "register information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response register(
            @RequestBody(required = true) String jsonRequest,
            HttpServletRequest request
    ){
        logger.info("Path =" +request.getRequestURI() + ", method = " + request.getMethod() +" INITIATED...");
        return this.marketInformationImpl.register(jsonRequest);
    }
}
