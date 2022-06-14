package com.project.market.controller;

import com.project.market.dto.req.ChangePasswordDtoRequest;
import com.project.market.dto.req.RegisterDtoRequest;
import com.project.market.dto.res.JwtDtoResponse;
import com.project.market.dto.res.Response;
import com.project.market.impl.service.register.RegisterImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = Logger.getLogger(RegisterController.class);
    @Autowired
    private RegisterImpl registerImpl;

    @ApiOperation(value = "Create Account", nickname = "createAccount", notes = "Create Account in database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/create-Account", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Response> insertProduct(
            @Valid @RequestBody(required = true) RegisterDtoRequest jsonRequest,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.registerImpl.register(jsonRequest);
    }

    @ApiOperation(value = "Get Data in JWTToken", nickname = "getDataInToken", notes = "Get Data in JWTToken")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody JwtDtoResponse getDataToken(
            @ApiParam(name = "token", value = "JWT token", required = true)
            @RequestParam(value = "token") String token,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.registerImpl.getDataToken(token);
    }

    @ApiOperation(value = "Change Password", nickname = "changePassword", notes = "Change Password in database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response changePassword(
            @Valid @RequestBody(required = true) ChangePasswordDtoRequest changePasswordDtoRequest,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.registerImpl.changePassword(changePasswordDtoRequest);
    }
}
