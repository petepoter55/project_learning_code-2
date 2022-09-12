package com.project.market.impl.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.market.constant.Constant;
import com.project.market.dto.req.product.ProductDiscountInsertDtpRequest;
import com.project.market.dto.req.product.ProductInsertDtoRequest;
import com.project.market.dto.res.Response;
import com.project.market.entity.product.ProductDiscount;
import com.project.market.entity.product.ProductMarket;
import com.project.market.repository.product.ProductDiscountRepository;
import com.project.market.repository.product.ProductMarketDescriptionRepository;
import com.project.market.repository.product.ProductMarketRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.validation.ValidationAbstract;
import com.project.market.impl.validation.ValidatorFactory;
import com.project.market.util.DateUtil;
import com.project.market.util.Util;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MarketProductsImpl {
    private static final Logger logger = Logger.getLogger(MarketProductsImpl.class);

    @Autowired
    private ValidatorFactory validatorFactory;
    @Autowired
    private ProductMarketRepository productMarketRepository;
    @Autowired
    private ProductMarketDescriptionRepository productMarketDescriptionRepository;
    @Autowired
    private ProductDiscountRepository productDiscountRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    public Response insertProduct(String jsonRequest) {
        logger.info("============= start insert product =============");
        ProductInsertDtoRequest productInsertDtoRequest;
        try {
            ValidationAbstract validationAbstract = validatorFactory.getValidator(Constant.INSERT_PRODUCT);
            validationAbstract.validate(Constant.INSERT_PRODUCT, jsonRequest);
            productInsertDtoRequest = objectMapper.readValue(jsonRequest, ProductInsertDtoRequest.class);

            ProductMarket productMarket = productMarketRepository.findByProductName(productInsertDtoRequest.getProductName());
            Boolean checkObject = ObjectUtils.isEmpty(productMarket);
            if (checkObject) {
                ProductMarket productMarkets = new ProductMarket();
                productMarkets.setProductCode(new Util().randomNumber(6));
                productMarkets.setProductName(productInsertDtoRequest.getProductName());
                productMarkets.setProductCount(productInsertDtoRequest.getProductCount());
                productMarkets.setType(productInsertDtoRequest.getType());
                productMarkets.setPrice(productInsertDtoRequest.getPrice());
                productMarkets.setActive("Y");
                productMarkets.setCreateDateTime(new DateUtil().getFormatsDateMilli());

                productMarketRepository.save(productMarkets);
            } else {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_PRODUCT_CHECKDATA_DUPLICATE);
            }

        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        } catch (JsonProcessingException | ParseException ex) {
            logger.error(String.format(Constant.THROW_EXCEPTION, ex.getMessage()));
            return Response.fail(String.valueOf(ex.hashCode()), ex.getMessage(), null);
        }

        logger.info("============= done insert product =============");
        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public Response deleteProductById(Integer productId) {
        logger.info("============= start delete product =============");
        logger.info("product id :" + productId);
        try {
            Optional<ProductMarket> productMarket = productMarketRepository.findById(productId);
            if (productMarket.isPresent()) {
                productMarketRepository.delete(productMarket.get());
            } else {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_PRODUCT_CHECKDATA_NOT_FOUND);
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        }

        logger.info("============= done delete product =============");
        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public List<ProductMarket> searchProduct(String productCode) {
        logger.info("============= start search product =============");
        logger.info("search By productCode LIKE :" + productCode);
        List<ProductMarket> productMarkets = new ArrayList<>();
        try {
            productMarkets = productMarketRepository.findByProductCode(productCode);
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
        logger.info("product size :" + productMarkets.size());
        logger.info("============= done search product =============");
        return productMarkets;
    }

    public Response insertProductDiscount(ProductDiscountInsertDtpRequest productDiscountInsertDtpRequest) {
        ProductDiscount productDiscount = new ProductDiscount();
        try {
            productDiscount.setProductCode(productDiscountInsertDtpRequest.getProductCode());
            productDiscount.setDiscount(productDiscountInsertDtpRequest.getDiscount());
            productDiscountRepository.save(productDiscount);
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }

        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public Response importDataExcel(MultipartFile files) throws IOException {
        logger.info("============= start import product =============");
        HashMap<String, Object> productMarket = new HashMap<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);        // get sheet in Excel By index
        try {
            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {

                    XSSFRow row = worksheet.getRow(index);
                    productMarket.put("productName", row.getCell(0).getStringCellValue());
                    productMarket.put("productCount", String.valueOf((int) row.getCell(1).getNumericCellValue()));
                    productMarket.put("type", String.valueOf((int) row.getCell(2).getNumericCellValue()));
                    productMarket.put("price", String.valueOf(row.getCell(3).getNumericCellValue()));
                    productMarket.put("active", row.getCell(4).getStringCellValue());

                    String jsonRequest = objectMapper.writeValueAsString(productMarket);
                    insertProduct(jsonRequest);
                }
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        } finally {
            workbook.close();
        }
        logger.info("============= done import product =============");
        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public void exportSearchUserByApproved(HttpServletResponse response, String productCode) throws IOException, ParseException {
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "product_" + new DateUtil().getFormatsDateString() + "_" + new Util().randomNumber(1) + ".xlsx");
        OutputStream outStream = null;

        try {
            // fix column name
            String[] columns = {
                    "ProductCode",
                    "ProductName",
                    "Active",
                    "Type-product",
                    "Price(Bath)",
                    "Total-product",
                    "CreateDateTime"
            };

            Workbook workbook = new XSSFWorkbook();

            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("master");

            // set style Header
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            // create header cell
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            List<ProductMarket> list = productMarketRepository.findByProductCode(productCode);

            // initialize data in row
            for (ProductMarket d : list) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(d.getProductCode());
                row.createCell(1).setCellValue(d.getProductName());
                row.createCell(2).setCellValue(d.getActive());
                row.createCell(3).setCellValue(d.getType());
                row.createCell(4).setCellValue(d.getPrice().toString());
                row.createCell(5).setCellValue(d.getProductCount().toString());
                row.createCell(6).setCellValue(new DateUtil().convertFullDateThai(d.getCreateDateTime()));
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // output response file name
            outStream = response.getOutputStream();
            workbook.write(outStream);
            outStream.flush();
            workbook.close();

        } catch (Exception e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        } finally {
            if (outStream != null) {
                outStream.close();
            }
        }
    }
}
