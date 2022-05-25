package com.project.market.controller;

import com.project.market.constant.Constant;
import com.project.market.dto.res.Response;
import com.project.market.entity.product.ProductMarket;
import com.project.market.impl.service.product.MarketProductImageImpl;
import com.project.market.impl.service.product.MarketProductsImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class MarketProductController {
    private static final Logger logger = Logger.getLogger(MarketProductController.class);

    @Autowired
    private MarketProductsImpl marketProducts;
    @Autowired
    private MarketProductImageImpl marketProductImage;

    @ApiOperation(value = "Insert Product", nickname = "insertProduct", notes = "Insert Product in database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response insertProduct(
            @RequestBody(required = true) String jsonRequest,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.marketProducts.insertProduct(jsonRequest);
    }

    @ApiOperation(value = "Delete Product Market Data", nickname = "deleteProduct", notes = "Delete Data Product Market in Database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response deleteProduct(
            @ApiParam(name = "productId", value = "Product id", required = true)
            @PathVariable(value = "productId") Integer productId,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.marketProducts.deleteProductById(productId);
    }

    @ApiOperation(value = "Search Product Market Data", nickname = "searchProduct", notes = "Search Data Product Market in Database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/search-product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductMarket> searchProduct(
            @ApiParam(name = "productCode", value = "Product code", required = true)
            @RequestParam(value = "productCode") String productCode,
            HttpServletRequest request
    ) {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        return this.marketProducts.searchProduct(productCode);
    }

    @ApiOperation(value = "Import Data Product Market", nickname = "importProduct", notes = "import Data Product Market in Database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/import-product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response importProduct(
            @ApiParam(name = "upFile", value = "File import Product", required = true)
            @RequestParam("upFile") MultipartFile file,
            HttpServletRequest request
    ) throws IOException {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        if (!("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet").equals(file.getContentType())) {
            throw new IOException(Constant.ERROR_FILE_TYPE_INVALID);
        }
        return this.marketProducts.importDataExcel(file);
    }

    @ApiOperation(value = "Insert Image Product Market", nickname = "insertImageProduct", notes = "Insert Image Product Market in Database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/insert-image", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response insertImageProduct(
            @ApiParam(name = "upFile", value = "File import Product", required = true)
            @RequestParam("upFile") MultipartFile file,
            @RequestParam("productCode") String productCode,
            HttpServletRequest request
    ) throws IOException {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        if (!("image/jpeg").equals(file.getContentType())) {
            throw new IOException(Constant.ERROR_FILE_TYPE_INVALID);
        }
        return this.marketProductImage.insertImageByProductCode(file, productCode);
    }

    @ApiOperation(value = "Export Product Market Data to Excel", nickname = "exportProduct", notes = "Export Data Product Market in Excel")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/export-product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void exportProductExcel(
            @ApiParam(name = "productCode", value = "Product code", required = true)
            @RequestParam(value = "productCode") String productCode,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ParseException {
        logger.info("Path =" + request.getRequestURI() + ", method = " + request.getMethod() + " INITIATED...");
        marketProducts.exportSearchUserByApproved(response, productCode);
    }
}
