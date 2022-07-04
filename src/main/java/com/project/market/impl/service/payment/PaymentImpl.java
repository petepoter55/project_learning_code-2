package com.project.market.impl.service.payment;

import com.project.market.constant.Constant;
import com.project.market.dto.req.payment.HistoryOrderDtoRequest;
import com.project.market.dto.req.payment.PaymentDtoRequest;
import com.project.market.dto.res.payment.HistoryOrderDtoResponse;
import com.project.market.dto.res.payment.PaymentDtoResponse;
import com.project.market.dto.res.payment.ProductDetail;
import com.project.market.entity.payment.HistoryOrder;
import com.project.market.entity.product.ProductDiscount;
import com.project.market.entity.product.ProductMarket;
import com.project.market.entity.repository.payment.HistoryOrderRepository;
import com.project.market.entity.repository.product.ProductDiscountRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PaymentImpl {
    private static final Logger logger = Logger.getLogger(PaymentImpl.class);

    @Autowired
    private ProductDiscountRepository productDiscountRepository;
    @Autowired
    private HistoryOrderRepository historyOrderRepository;

    public PaymentDtoResponse viewOrder(PaymentDtoRequest paymentDtoRequest) {
        PaymentDtoResponse paymentDtoResponse = new PaymentDtoResponse();
        List<ProductDetail> productDetails = new ArrayList<>();
        List<ProductDiscount> productDiscounts = new ArrayList<>();

        try {
            if (paymentDtoRequest.getProductMarketList() != null) {
                paymentDtoRequest.getProductMarketList().forEach(m -> {
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setProductCode(m.getProductCode());

                    ProductDiscount productDiscount = productDiscountRepository.findByProductCode(m.getProductCode());
                    if (productDiscount != null) {
                        productDiscounts.add(productDiscount);
                    }
                    productDetail.setProductName(m.getProductName());
                    productDetail.setPrice(m.getPrice());
                    productDetails.add(productDetail);
                });
                paymentDtoResponse.setProductDetails(productDetails);
                paymentDtoResponse.setSum(paymentDtoRequest.getProductMarketList().stream().map(ProductMarket::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
                paymentDtoResponse.setDiscount(productDiscounts.stream().map(ProductDiscount::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add));
                paymentDtoResponse.setNetAmount(paymentDtoResponse.getSum().subtract(paymentDtoResponse.getDiscount()));
                insertOrderHistory(paymentDtoRequest.getProductMarketList(), paymentDtoRequest.getUsername());
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }

        return paymentDtoResponse;
    }

    public void insertOrderHistory(List<ProductMarket> productMarketList, String username) {
        try {
            productMarketList.forEach(m -> {
                HistoryOrder historyOrder = new HistoryOrder();
                historyOrder.setUsername(username);
                historyOrder.setProductCode(m.getProductCode());
                historyOrder.setProductName(m.getProductName());
                historyOrder.setPrice(m.getPrice());
                try {
                    historyOrder.setCreateDateTime(new DateUtil().getFormatsDateMilli());
                } catch (ParseException e) {
                    logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
                }
                historyOrderRepository.save(historyOrder);
            });
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
    }

    public List<HistoryOrderDtoResponse> searchDetailHistoryOrder(HistoryOrderDtoRequest historyOrderDtoRequest) {
        List<HistoryOrderDtoResponse> historyOrderDtoResponseList = new ArrayList<>();
        List<HistoryOrder> listFilter;

        try {
            List<HistoryOrder> historyOrderList = historyOrderRepository.findAll();
            if (!historyOrderList.isEmpty()) {
                if (historyOrderDtoRequest.getUsername() != null) {
                    listFilter = historyOrderList.stream().filter(f -> historyOrderDtoRequest.getUsername().equals(f.getUsername())).collect(Collectors.toList());

                    if (historyOrderDtoRequest.getStartDate() != null && historyOrderDtoRequest.getEndDate() != null) {
                        Date startDates = new DateUtil().generateDatetime(historyOrderDtoRequest.getStartDate());
                        Date endDates = new DateUtil().generateDatetime(historyOrderDtoRequest.getEndDate());

                        listFilter = historyOrderList.stream()
                                .filter(f -> historyOrderDtoRequest.getUsername().equals(f.getUsername()))
                                .filter(a -> a.getCreateDateTime().getTime() > (startDates.getTime()))
                                .filter(c -> c.getCreateDateTime().getTime() < (endDates.getTime()))
                                .collect(Collectors.toList());
                    }
                } else {
                    listFilter = historyOrderList;
                }

                listFilter.forEach(m -> {
                    HistoryOrderDtoResponse historyOrderDtoResponse = new HistoryOrderDtoResponse();
                    historyOrderDtoResponse.setOrderId(m.getOrderId());
                    historyOrderDtoResponse.setProductCode(m.getProductCode());
                    historyOrderDtoResponse.setProductName(m.getProductName());
                    historyOrderDtoResponse.setPrice(m.getPrice());
                    historyOrderDtoResponseList.add(historyOrderDtoResponse);
                });
            }
        } catch (ResponseException | ParseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }

        return historyOrderDtoResponseList.stream().sorted(Comparator.comparing(HistoryOrderDtoResponse::getPrice)).collect(Collectors.toList());
    }

    public Map<String, Long> summaryOrderHistory(String username) {
        Map<String, Long> groupingCount = new HashMap<>();
        List<HistoryOrder> historyOrderList = historyOrderRepository.findByUsername(username);
        try {
            if (!historyOrderList.isEmpty()) {
                groupingCount = historyOrderList.stream().collect(Collectors.groupingBy(HistoryOrder::getProductName, Collectors.counting()));
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }

        return groupingCount;
    }
}
