package com.project.market.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.market.constant.Constant;
import com.project.market.dto.req.RegisterDtoRequest;
import com.project.market.dto.res.Response;
import com.project.market.entity.information.InformationMarket;
import com.project.market.entity.repository.InformationMarketRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.validation.ValidationAbstract;
import com.project.market.impl.validation.ValidatorFactory;
import com.project.market.util.DateUtil;
import com.project.market.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;


@Service
public class MarketInformationImpl {
    private static final Logger logger = Logger.getLogger(MarketInformationImpl.class);

    @Autowired
    private ValidatorFactory validatorFactory;
    @Autowired
    private InformationMarketRepository informationMarketRepository;

    public Response register(String jsonRequest) throws ResponseException{
        RegisterDtoRequest registerDtoRequest;
        try {
            //validate input
            ValidationAbstract validator = validatorFactory.getValidator(Constant.REGISTER);
            validator.validate(Constant.REGISTER,jsonRequest);
            registerDtoRequest = new ObjectMapper().readValue(jsonRequest,RegisterDtoRequest.class);

            InformationMarket informationMarket = this.informationMarketRepository.findByFirstnameAndLastname(registerDtoRequest.getFirstname(),registerDtoRequest.getLastname());

            boolean checkDataRegister = ObjectUtils.isEmpty(informationMarket);

            if(checkDataRegister){
                setObjectRegister(registerDtoRequest);
            }else {
                return new Response(Constant.STATUS_FALSE,Constant.ERROR_REGISTER_CHECKDATA_DUPLICATE,Constant.STATUS_CODE_FAIL);
            }
        }catch (ResponseException | JsonProcessingException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
        return new Response(Constant.STATUS_SUCCESS,Constant.SUCCESS,Constant.STATUS_CODE_SUCCESS);
    }

    public void setObjectRegister(RegisterDtoRequest registerDtoRequest){
        String RefNo = String.format(Constant.REFERENCE_NO,Integer.parseInt(new Util().randomNumber(3)));
        String typeRegister = null;
        try {
            if(("1").equals(registerDtoRequest.getType())){
                typeRegister = Constant.TYPE_REGISTER_1;
            }else if(("2").equals(registerDtoRequest.getType())){
                typeRegister = Constant.TYPE_REGISTER_2;
            }else {
                typeRegister = Constant.TYPE_REGISTER_1;
            }

            InformationMarket informationMarket = new InformationMarket();
            informationMarket.setFirstName(registerDtoRequest.getFirstname());
            informationMarket.setLastName(registerDtoRequest.getLastname());
            informationMarket.setReferenceNo(RefNo);
            informationMarket.setEmail(registerDtoRequest.getEmail());
            informationMarket.setRoad(registerDtoRequest.getRoad());
            informationMarket.setCreateDateTime(new DateUtil().getFormatsDateMilli());
            informationMarket.setTelephone(registerDtoRequest.getTelephone());
            informationMarket.setAddress1(registerDtoRequest.getAddress1());
            informationMarket.setAddress2(registerDtoRequest.getAddress2());
            informationMarket.setDistrictName(registerDtoRequest.getDistrictName());
            informationMarket.setProvinceName(registerDtoRequest.getProvinceName());
            informationMarket.setSubProvinceName(registerDtoRequest.getSubProvinceName());
            informationMarket.setPostcode(registerDtoRequest.getPostcode());
            informationMarket.setStatus(Constant.STATUS_CREATE_BEGIN);
            informationMarket.setType(typeRegister);

            informationMarketRepository.save(informationMarket);
        }catch (ResponseException | ParseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
    }

}
