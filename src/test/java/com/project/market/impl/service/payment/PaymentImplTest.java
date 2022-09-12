package com.project.market.impl.service.payment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.market.constant.Constant;
import com.project.market.dto.res.payment.PaymentDtoResponse;
import com.project.market.repository.payment.HistoryOrderRepository;
import com.project.market.repository.product.ProductDiscountRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.model.request.ViewOrderTestRequest;
import com.project.market.model.response.ViewOrderTestResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PaymentImplTest {
    private final static Logger logger = Logger.getLogger(PaymentImplTest.class);
    @Mock
    private ProductDiscountRepository productDiscountRepository;
    @Mock
    private HistoryOrderRepository historyOrderRepository;
    @InjectMocks
    private PaymentImpl paymentImpl;

    private void setup(ViewOrderTestRequest viewOrderTestRequest) {
        when(productDiscountRepository.findByProductCode(anyString())).thenReturn(viewOrderTestRequest.getProductDiscount());
        when(historyOrderRepository.save(any())).thenReturn(null);
    }

    @Test
    public void viewOrderTest() throws Exception {
        File[] files = readTestCase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(simpleDateFormat);
        for (File file : files) {
            try {
                //ARRANGE : declare value
                ViewOrderTestRequest viewOrderTestRequest = mapper.readValue(file, ViewOrderTestRequest.class);
                setup(viewOrderTestRequest);
                //ACT : assign value in method test
                PaymentDtoResponse paymentDtoResponse = paymentImpl.viewOrder(viewOrderTestRequest.getPaymentDtoRequest());
                ViewOrderTestResponse viewOrderTestResponse = mapper.readValue(FileUtils.readFileToString(new File(FilenameUtils.concat("./resources/payment/service/response/", file.getName())), StandardCharsets.UTF_8), ViewOrderTestResponse.class);
                //ASSERT : test assert
                String actual = new ObjectMapper().writeValueAsString(paymentDtoResponse);
                String expected = new ObjectMapper().writeValueAsString(viewOrderTestResponse);
                logger.info("actual => " + actual);
                logger.info("expected => " + expected);

                JSONCompareResult result = JSONCompare.compareJSON(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
                logger.info("result => " + result.passed());
                Assert.assertTrue(result.passed());

            } catch (ResponseException e) {
                logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            } finally {
                destroy();
            }
        }
    }

    private File[] readTestCase() throws Exception {
        File folder = new File("./resources/payment/service/request/");
        return folder.listFiles();
    }

    private void destroy() throws ResponseException {
        this.historyOrderRepository.deleteAll();
        reset(this.historyOrderRepository);
    }
}