package com.project.market.impl.service.information;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.market.constant.Constant;
import com.project.market.dto.req.information.InformationInsertDtoRequest;
import com.project.market.dto.req.information.SearchDtoRequest;
import com.project.market.dto.req.information.UpdateRegisterDtoRequest;
import com.project.market.dto.res.Response;
import com.project.market.entity.information.InformationMarket;
import com.project.market.repository.information.InformationMarketRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.validation.ValidationAbstract;
import com.project.market.impl.validation.ValidatorFactory;
import com.project.market.util.DateUtil;
import com.project.market.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MarketInformationImpl {
    private static final Logger logger = Logger.getLogger(MarketInformationImpl.class);

    @Autowired
    private ValidatorFactory validatorFactory;
    @Autowired
    private InformationMarketRepository informationMarketRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EntityManager entityManager;

    public Response register(String jsonRequest) throws ResponseException {
        logger.info("============= start register =============");
        InformationInsertDtoRequest informationInsertDtoRequest;
        try {
            //validate input
            ValidationAbstract validator = validatorFactory.getValidator(Constant.REGISTER);
            validator.validate(Constant.REGISTER, jsonRequest);
            informationInsertDtoRequest = objectMapper.readValue(jsonRequest, InformationInsertDtoRequest.class);

            InformationMarket informationMarket = this.informationMarketRepository.findByFirstnameAndLastname(informationInsertDtoRequest.getFirstname(), informationInsertDtoRequest.getLastname());

            boolean checkDataRegister = ObjectUtils.isEmpty(informationMarket);
            logger.info("checkDataRegister : " + checkDataRegister);
            if (checkDataRegister) {
                setObjectRegister(informationInsertDtoRequest);
            } else {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_REGISTER_CHECKDATA_DUPLICATE);
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        } catch (JsonProcessingException ex) {
            logger.error(String.format(Constant.THROW_EXCEPTION, ex.getMessage()));
            return Response.fail(String.valueOf(ex.hashCode()), ex.getMessage(), null);
        }
        logger.info("============= done register =============");
        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public void setObjectRegister(InformationInsertDtoRequest informationInsertDtoRequest) {
        String RefNo = String.format(Constant.REFERENCE_NO, Integer.parseInt(new Util().randomNumber(3)));
        String typeRegister = null;
        try {
            if (("1").equals(informationInsertDtoRequest.getType())) {
                typeRegister = Constant.TYPE_REGISTER_1;
            } else if (("2").equals(informationInsertDtoRequest.getType())) {
                typeRegister = Constant.TYPE_REGISTER_2;
            } else {
                typeRegister = Constant.TYPE_REGISTER_1;
            }

            InformationMarket informationMarket = new InformationMarket();
            informationMarket.setFirstName(informationInsertDtoRequest.getFirstname());
            informationMarket.setLastName(informationInsertDtoRequest.getLastname());
            informationMarket.setReferenceNo(RefNo);
            informationMarket.setEmail(informationInsertDtoRequest.getEmail());
            informationMarket.setRoad(informationInsertDtoRequest.getRoad());
            informationMarket.setCreateDateTime(new DateUtil().getFormatsDateMilli());
            informationMarket.setTelephone(informationInsertDtoRequest.getTelephone());
            informationMarket.setAddress1(informationInsertDtoRequest.getAddress1());
            informationMarket.setAddress2(informationInsertDtoRequest.getAddress2());
            informationMarket.setDistrictName(informationInsertDtoRequest.getDistrictName());
            informationMarket.setProvinceName(informationInsertDtoRequest.getProvinceName());
            informationMarket.setSubProvinceName(informationInsertDtoRequest.getSubProvinceName());
            informationMarket.setPostcode(informationInsertDtoRequest.getPostcode());
            informationMarket.setStatus(Constant.STATUS_CREATE_BEGIN);
            informationMarket.setType(typeRegister);

            informationMarketRepository.save(informationMarket);
        } catch (ResponseException | ParseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
    }

    public ResponseEntity<String> deleteById(Integer id) {
        logger.info("============= start delete Data =============");
        logger.info("register id : " + id);
        try {
            Optional<InformationMarket> informationMarket = informationMarketRepository.findById(id);
            if (informationMarket.isPresent()) {
                informationMarketRepository.deleteById(id);
            } else {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_REGISTER_CHECKDATA_FOUND);
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        logger.info("============= done delete Data =============");
        return new ResponseEntity<>(Constant.SUCCESS, HttpStatus.OK);
    }

    public Response updateRegisterData(String jsonRequest) {
        logger.info("============= start updateRegisterData =============");
        try {
            //validation input
            ValidationAbstract validationAbstract = validatorFactory.getValidator(Constant.UPDATE_REGISTER);
            validationAbstract.validate(Constant.UPDATE_REGISTER, jsonRequest);
            UpdateRegisterDtoRequest updateRegisterDtoRequest = objectMapper.readValue(jsonRequest, UpdateRegisterDtoRequest.class);

            Optional<InformationMarket> informationMarkets = informationMarketRepository.findById(Integer.parseInt(updateRegisterDtoRequest.getId()));
            if (informationMarkets.isPresent()) {
                InformationMarket informationMarket = informationMarkets.get();
                informationMarket.setFirstName(updateRegisterDtoRequest.getFirstname());
                informationMarket.setLastName(updateRegisterDtoRequest.getLastname());
                informationMarket.setEmail(updateRegisterDtoRequest.getEmail());
                informationMarket.setRoad(updateRegisterDtoRequest.getRoad());
                informationMarket.setUpdateDateTime(new DateUtil().getFormatsDateMilli());
                informationMarket.setTelephone(updateRegisterDtoRequest.getTelephone());
                informationMarket.setAddress1(updateRegisterDtoRequest.getAddress1());
                informationMarket.setAddress2(updateRegisterDtoRequest.getAddress2());
                informationMarket.setDistrictName(updateRegisterDtoRequest.getDistrictName());
                informationMarket.setProvinceName(updateRegisterDtoRequest.getProvinceName());
                informationMarket.setSubProvinceName(updateRegisterDtoRequest.getSubProvinceName());
                informationMarket.setPostcode(updateRegisterDtoRequest.getPostcode());

                informationMarketRepository.save(informationMarket);
            }

        } catch (ParseException | ResponseException | JsonProcessingException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(String.valueOf(e.hashCode()), e.getMessage(), null);
        }
        logger.info("============= done updateRegisterData =============");
        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public List<InformationMarket> searchByInput(SearchDtoRequest searchDtoRequest) {
        logger.info("============= start searchByInput =============");
        List<InformationMarket> informationMarketList = new ArrayList<>();
        try {
            //createQuery
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<InformationMarket> criteriaQuery = criteriaBuilder.createQuery(InformationMarket.class);
            Root<InformationMarket> informationMarketRoot = criteriaQuery.from(InformationMarket.class);

            //list condition
            List<Predicate> predicates = new ArrayList<>();
            if (searchDtoRequest.getFirstName() != null && !("").equals(searchDtoRequest.getFirstName())) {
                logger.info("firstName: " + searchDtoRequest.getFirstName());
                predicates.add(criteriaBuilder.like(informationMarketRoot.get("firstName"), "%" + searchDtoRequest.getFirstName() + "%"));
            }
            if (searchDtoRequest.getLastName() != null && !("").equals(searchDtoRequest.getFirstName())) {
                logger.info("lastName: " + searchDtoRequest.getLastName());
                predicates.add(criteriaBuilder.like(informationMarketRoot.get("lastName"), "%" + searchDtoRequest.getLastName() + "%"));
            }
            if (searchDtoRequest.getEmail() != null) {
                logger.info("email: " + searchDtoRequest.getEmail());
                predicates.add(criteriaBuilder.like(informationMarketRoot.get("email"), "%" + searchDtoRequest.getEmail() + "%"));
            }
            if (searchDtoRequest.getReferenceNo() != null) {
                logger.info("referenceNo: " + searchDtoRequest.getReferenceNo());
                predicates.add(criteriaBuilder.equal(informationMarketRoot.get("referenceNo"), searchDtoRequest.getReferenceNo()));
            }
            if (searchDtoRequest.getCreateDateTime() != null) {
                logger.info("createDatetime: " + searchDtoRequest.getCreateDateTime());
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(informationMarketRoot.get("createDatetime"), searchDtoRequest.getCreateDateTime()));
            }

            //execute query
            //TODO when send argument value "" but query return result ? >> why
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            informationMarketList = entityManager.createQuery(criteriaQuery).getResultList();

        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
        logger.info("size informationMarketList : " + informationMarketList.size());
        logger.info("============= done searchByInput =============");
        return informationMarketList;
    }
}
