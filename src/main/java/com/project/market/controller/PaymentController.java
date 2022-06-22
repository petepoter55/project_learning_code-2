package com.project.market.controller;

import com.project.market.dto.req.payment.HistoryOrderDtoRequest;
import com.project.market.dto.req.payment.PaymentDtoRequest;
import com.project.market.dto.res.payment.HistoryOrderDtoResponse;
import com.project.market.dto.res.payment.PaymentDtoResponse;
import com.project.market.impl.service.payment.PaymentImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private static final Logger logger = Logger.getLogger(PaymentController.class);

    @Autowired
    private PaymentImpl paymentImpl;

    //    @Consumes ใช้สำหรับ request และ @Produces ใช้สำหรับ response
    @ApiOperation(value = "View Details Order", nickname = "viewDetailOrder", notes = "View Details Order")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/order-detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PaymentDtoResponse orderDetails(
            @Valid @RequestBody(required = true) PaymentDtoRequest paymentDtoRequest,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.paymentImpl.viewOrder(paymentDtoRequest);
    }

    @ApiOperation(value = "Search Order History", nickname = "searchOrderHistory", notes = "Search Order History")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/search-order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<HistoryOrderDtoResponse> searchOrderHistory(
            @Valid @RequestBody(required = true) HistoryOrderDtoRequest historyOrderDtoRequest,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.paymentImpl.searchHistoryOrder(historyOrderDtoRequest);
    }
}
