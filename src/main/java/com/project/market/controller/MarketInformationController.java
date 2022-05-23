package com.project.market.controller;

import com.project.market.dto.req.SearchDtoRequest;
import com.project.market.dto.res.Response;
import com.project.market.entity.information.InformationMarket;
import com.project.market.impl.service.information.MarketInformationImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/information")
public class MarketInformationController {
    private static final Logger logger = Logger.getLogger(MarketInformationController.class);

    @Autowired
    private MarketInformationImpl marketInformationImpl;

//    @Consumes ใช้สำหรับ request และ @Produces ใช้สำหรับ response
    @ApiOperation(value = "Register Data Information", nickname = "registerData", notes = "register information in database")
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

    @ApiOperation(value = "Delete Register Data information", nickname = "deleteRegisterInFor", notes = "Delete Data register information in Database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/delete/{registerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRegister(
            @ApiParam(name = "registerId", value = "Register id", required = true)
            @PathVariable(value = "registerId") Integer registerId,
            HttpServletRequest request
    ){
        logger.info("Path =" +request.getRequestURI() + ", method = " + request.getMethod() +" INITIATED...");
        return this.marketInformationImpl.deleteById(registerId);
    }

    @ApiOperation(value = "Update Register Data Information", nickname = "updateRegisterData", notes = "update register information in database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/update", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response updateDataRegister(
            @RequestBody(required = true) String jsonRequest,
            HttpServletRequest request
    ){
        logger.info("Path =" +request.getRequestURI() + ", method = " + request.getMethod() +" INITIATED...");
        return this.marketInformationImpl.updateRegisterData(jsonRequest);
    }

    @ApiOperation(value = "Search Data Information By Input Value", nickname = "searchRegisterData", notes = "search register information in database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<InformationMarket> searchByInput(
            @RequestBody(required = true) SearchDtoRequest searchDtoRequest,
            HttpServletRequest request
    ){
        logger.info("Path =" +request.getRequestURI() + ", method = " + request.getMethod() +" INITIATED...");
        return this.marketInformationImpl.searchByInput(searchDtoRequest);
    }
}
