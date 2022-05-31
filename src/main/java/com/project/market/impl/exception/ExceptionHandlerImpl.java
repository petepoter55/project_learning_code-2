package com.scb.api.ent.customer.services.origination.impl.exception;

import com.scb.api.common.framework.domain.exception.ErrorInfo;
import com.scb.api.common.framework.domain.exception.ResourceConflictException;
import com.scb.api.common.framework.domain.exception.ResourceNotFoundException;
import com.scb.api.ent.customer.services.origination.constant.BackendRespCode;
import com.scb.api.ent.customer.services.origination.constant.ErrorMessages;
import com.scb.api.ent.customer.services.origination.domain.purposefulloan.wsdl.inquiry.InquiryDocRsType;
import com.scb.api.ent.customer.services.origination.domain.unsecured.wsdl.DocRsType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ExceptionHandlerImpl {

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.wsdl.DocRsType> getLeadOriginationResponseHanlder() {
        ExceptionHandler<DocRsType> handler = (response) -> {
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMsg();
            switch (responseCode) {
                case BackendRespCode.SUCCESS:
                    if (response.getResult().getPyID() == null)
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.PYID_WS_TECHNICAL_ERROR_DESC,
                                String.format(ErrorMessages.PYID_WS_TECHNICAL_ERROR_MOREINFO, responseCode, responseMessage)
                        );
                    break;

                case BackendRespCode.FAILED:
                    if (Objects.nonNull(response.getResult())
                            && StringUtils.isNotBlank(response.getResult().getAutoReject())
                            && response.getResult().getAutoReject().equalsIgnoreCase("true")) {  //API-27114 .. 2018-09-18
                        throw new ResourceConflictException(
                                ErrorMessages.UNSECURED_LEADS_RESPONSE_AUTOREJECT_BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, responseCode, responseMessage)
                        );
                    } else {
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, responseCode, responseMessage)
                        );
                    }

                default:
                    throw new ResourceConflictException(
                            ErrorMessages.BUSINESS_ERROR,
                            ErrorMessages.BUSINESS_ERROR_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.BUSINESS_ERROR_DESC,
                            String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, responseCode, responseMessage)
                    );
            }

        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.v2.wsdl.DocRsType> getLeadOriginationV2ResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.v2.wsdl.DocRsType> handler = (response) -> {
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMsg();
            switch (responseCode) {
                case BackendRespCode.SUCCESS:
                    if (response.getResult().getPyID() == null)
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.PYID_WS_TECHNICAL_ERROR_DESC,
                                String.format(ErrorMessages.PYID_WS_TECHNICAL_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    break;

                case BackendRespCode.FAILED:
                    if (Objects.nonNull(response.getResult())
                            && StringUtils.isNotBlank(response.getResult().getAutoReject())
                            && response.getResult().getAutoReject().equalsIgnoreCase("true")) {  //API-27114 .. 2018-09-18
                        throw new ResourceConflictException(
                                ErrorMessages.UNSECURED_LEADS_RESPONSE_AUTOREJECT_BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    } else {
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    }

                default:
                    throw new ResourceConflictException(
                            ErrorMessages.BUSINESS_ERROR,
                            ErrorMessages.BUSINESS_ERROR_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.BUSINESS_ERROR_DESC,
                            String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                            responseCode,
                            responseMessage
                    );
            }
        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.v3.wsdl.DocRsType> getLeadOriginationV3ResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.v3.wsdl.DocRsType> handler = (response) -> {
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMsg();
            switch (responseCode) {
                case BackendRespCode.SUCCESS:
                    if (response.getResult().getPyID() == null)
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.PYID_WS_TECHNICAL_ERROR_DESC,
                                String.format(ErrorMessages.PYID_WS_TECHNICAL_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    break;

                case BackendRespCode.FAILED:
                    if (Objects.nonNull(response.getResult())
                            && StringUtils.isNotBlank(response.getResult().getAutoReject())
                            && response.getResult().getAutoReject().equalsIgnoreCase("true")) {  //API-27114 .. 2018-09-18
                        throw new ResourceConflictException(
                                ErrorMessages.UNSECURED_LEADS_RESPONSE_AUTOREJECT_BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    } else {
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    }

                default:
                    throw new ResourceConflictException(
                            ErrorMessages.BUSINESS_ERROR,
                            ErrorMessages.BUSINESS_ERROR_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.BUSINESS_ERROR_DESC,
                            String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                            responseCode,
                            responseMessage
                    );
            }
        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.v5.wsdl.DocRsType> getLeadOriginationV5ResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.unsecured.v5.wsdl.DocRsType> handler = (response) -> {
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMsg();
            switch (responseCode) {
                case BackendRespCode.SUCCESS:
                    if (response.getResult().getPyID() == null)
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.PYID_WS_TECHNICAL_ERROR_DESC,
                                String.format(ErrorMessages.PYID_WS_TECHNICAL_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    break;

                case BackendRespCode.FAILED:
                    if (Objects.nonNull(response.getResult())
                            && StringUtils.isNotBlank(response.getResult().getAutoReject())
                            && response.getResult().getAutoReject().equalsIgnoreCase("true")) {  //API-27114 .. 2018-09-18
                        throw new ResourceConflictException(
                                ErrorMessages.UNSECURED_LEADS_RESPONSE_AUTOREJECT_BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    } else {
                        throw new ResourceConflictException(
                                ErrorMessages.BUSINESS_ERROR,
                                ErrorMessages.BUSINESS_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.BUSINESS_ERROR_DESC,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                                responseCode,
                                responseMessage
                        );
                    }

                default:
                    throw new ResourceConflictException(
                            ErrorMessages.BUSINESS_ERROR,
                            ErrorMessages.BUSINESS_ERROR_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.BUSINESS_ERROR_DESC,
                            String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_V2, responseCode, responseMessage),
                            responseCode,
                            responseMessage
                    );
            }
        };
        return handler;
    }


    public static void invalidResponseFromWebService(String fieldError, String responseCode, String responseMesage)
            throws ResourceConflictException {
        switch (fieldError) {
            case "CREDITLIMIT":
                throw new ResourceConflictException(
                        ErrorMessages.BUSINESS_ERROR,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.CREDITLIMIT_WS_TECHNICAL_ERROR_DESC,
                        String.format(ErrorMessages.CREDITLIMIT_WS_TECHNICAL_ERROR_MOREINFO,
                                responseCode,
                                responseMesage)
                );

            case "AUTOREJECT":
                throw new ResourceConflictException(
                        ErrorMessages.BUSINESS_ERROR,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.AUTOREJECT_WS_TECHNICAL_ERROR_DESC,
                        String.format(ErrorMessages.AUTOREJECT_WS_TECHNICAL_ERROR_MOREINFO,
                                responseCode,
                                responseMesage)
                );

            default:
                throw new ResourceConflictException(
                        ErrorMessages.BUSINESS_ERROR,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.INVALID_RESPONSE_WS_BUSINESS_ERROR_DESC,
                        String.format(ErrorMessages.INVALID_RESPONSE_WS_BUSINESS_ERROR_MOREINFO,
                                responseCode,
                                responseMesage)
                );
        }
    }

    public static void nullResponseFromDB(String value, String fields, String original) throws ResourceConflictException {
        switch (fields) {
            case "OCCUPATION_CODE":
                throw new ResourceConflictException(
                        ErrorMessages.OCCUPATION_CODE_IS_NOT_FOUND_ERROR_CODE,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.OCCUPATION_CODE_IS_NOT_FOUND_ERROR_DESC,
                        String.format(ErrorMessages.OCCUPATION_CODE_IS_NOT_FOUND_ERROR_MOREINFO,
                                original,
                                value,
                                fields)
                );

            case "PROFESSIONAL":
                throw new ResourceConflictException(
                        ErrorMessages.OCCUPATION_CODE_IS_NOT_FOUND_ERROR_CODE,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.OCCUPATION_CODE_IS_NOT_FOUND_ERROR_DESC,
                        String.format(ErrorMessages.OCCUPATION_CODE_IS_NOT_FOUND_ERROR_MOREINFO,
                                original,
                                value,
                                fields)
                );

            case "RM_POSITION_CODE":
                throw new ResourceConflictException(
                        ErrorMessages.POSITION_CODE_IS_NOT_FOUND_ERROR_CODE,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.POSITION_CODE_IS_NOT_FOUND_ERROR_DESC,
                        String.format(ErrorMessages.POSITION_CODE_IS_NOT_FOUND_ERROR_MOREINFO,
                                original,
                                value,
                                fields)
                );

            case "LEAD_POSITION_CODE":
                throw new ResourceConflictException(
                        ErrorMessages.POSITION_CODE_IS_NOT_FOUND_ERROR_CODE,
                        ErrorMessages.BUSINESS_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        ErrorMessages.POSITION_CODE_IS_NOT_FOUND_ERROR_DESC,
                        String.format(ErrorMessages.POSITION_CODE_IS_NOT_FOUND_ERROR_MOREINFO,
                                original,
                                value,
                                fields)
                );


        }

    }

    public static ExceptionHandler<InquiryDocRsType> getPurposefulLoanInquiryResponseHandler() {
        ExceptionHandler<InquiryDocRsType> handler = (response) -> {
            final String resCode = response.getResCode();
            switch (resCode) {
                case BackendRespCode.SUCCESS:
                    break;
                case BackendRespCode.FOUND:
                    break;
                case BackendRespCode.NOT_FOUND:
                    throw new ResourceNotFoundException(
                            ErrorMessages.RECORD_NOT_FOUND_ERROR,
                            ErrorMessages.RECORD_NOT_FOUND_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.RECORD_NOT_FOUND_DESC,
                            ErrorMessages.RECORD_NOT_FOUND_DESC);
                default:
                    final String responseMessage = response.getResMessage();
                    throw new ResourceConflictException(
                            ErrorMessages.ALL_OTHERS_ERROR,
                            ErrorMessages.ALL_OTHERS_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.ALL_OTHERS_DESC,
                            String.format(ErrorMessages.ALL_OTHERS_MOREINFO, resCode, responseMessage)
                    );
            }
        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.inquiry.wsdl.InquiryDocRsType> getMortgageInquiryResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.inquiry.wsdl.InquiryDocRsType> handler = (response) -> {
            final String resCode = response.getResCode();
            switch (resCode) {
                case BackendRespCode.SUCCESS:
                    break;
                case BackendRespCode.FOUND:
                    break;
                case BackendRespCode.NOT_FOUND:
                    throw new ResourceNotFoundException(
                            ErrorMessages.RECORD_NOT_FOUND_ERROR,
                            ErrorMessages.RECORD_NOT_FOUND_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.RECORD_NOT_FOUND_DESC,
                            ErrorMessages.RECORD_NOT_FOUND_DESC);
                default:
                    final String responseMessage = response.getResMessage();
                    throw new ResourceConflictException(
                            ErrorMessages.ALL_OTHERS_ERROR,
                            ErrorMessages.ALL_OTHERS_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.ALL_OTHERS_DESC,
                            String.format(ErrorMessages.ALL_OTHERS_MOREINFO, resCode, responseMessage)
                    );
            }
        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.masterdata.wsdl.GETResponseType> getMortgageMasterDataResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.masterdata.wsdl.GETResponseType> handler = (response) -> {
            final String resCode = response.getResCode();
            switch (resCode) {
                case BackendRespCode.DATA_TABLE_SUCCESS:
                    break;
                case BackendRespCode.DATA_TABLE_UNKNOWN:
                    throw new ResourceNotFoundException(
                            ErrorMessages.MO_MASTER_TABLE_UNKNOWN,
                            ErrorMessages.MO_MASTER_TABLE_UNKNOWN_DESC,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.MO_MASTER_TABLE_UNKNOWN_MOREINFO,
                            ErrorMessages.MO_MASTER_TABLE_UNKNOWN_MOREINFO);
                default:
                    final String responseMessage = response.getResMessage();
                    throw new ResourceConflictException(
                            ErrorMessages.ALL_OTHERS_ERROR,
                            ErrorMessages.ALL_OTHERS_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            ErrorMessages.ALL_OTHERS_DESC,
                            String.format(ErrorMessages.ALL_OTHERS_MOREINFO, resCode, responseMessage)
                    );
            }
        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.purposefulloan.wsdl.submission.DocRsType> getPPFSubmissionLeadsResponseHanlder() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.purposefulloan.wsdl.submission.DocRsType> handler = (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMessage();
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0020_FAILED_INVALID_INPUT:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_CODE,
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0020_FAILED_PENDING_CA_FOUND:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_CODE,
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0020_FAILED_PROCESS_FAILURE:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_CODE,
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0020_FAILED_WORK_IN_PROGRESS:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_CODE,
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0020_FAILED_CRITERIA_NOT_MET:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_CODE,
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    default:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_CODE,
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                }
            }
        };

        return handler;
    }

    /**
     * @return ExceptionHandler for DocRsType for validateNCBLeads
     */
    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.ncb.wsdl.DocRsType> getSCBLOSIntAPIMortgageNewLoanHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.ncb.wsdl.DocRsType> handler = (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseMessage = response.getResMessage();
            final String responseCode = response.getResCode();
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0030_SUCCESS:
                        break;

                    case BackendRespCode.R0030_FAILED_INVALID_INPUT:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0030_INVALID_INPUT_ERROR_CODE,
                                ErrorMessages.SOAP_R0030_INVALID_INPUT_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0030_INVALID_INPUT_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0030_FAILED_PENDING_CA_FOUND:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0030_PENDING_CA_FOUND_CODE,
                                ErrorMessages.SOAP_R0030_PENDING_CA_FOUND_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0030_PENDING_CA_FOUND_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0030_FAILED_PROCESS_FAILURE:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0030_PROCESS_FAILURE_CODE,
                                ErrorMessages.SOAP_R0030_PROCESS_FAILURE_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0030_PROCESS_FAILURE_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0030_FAILED_WORK_IN_PROGRESS:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0030_WORK_IN_PROGRESS_CODE,
                                ErrorMessages.SOAP_R0030_WORK_IN_PROGRESS_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0030_WORK_IN_PROGRESS_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    case BackendRespCode.R0030_FAILED_CRITERIA_NOT_MET:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0030_CRITERIA_NOT_MET_CODE,
                                ErrorMessages.SOAP_R0030_CRITERIA_NOT_MET_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0030_CRITERIA_NOT_MET_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                    default:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0030_OTHER_ERROR_CODE,
                                ErrorMessages.SOAP_R0030_OTHER_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0030_OTHER_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO, errorCode, responseMessage)
                        );
                }
            }
        };
        return handler;
    }

    /**
     * @return ExceptionHandler for DocRsType for inquireMortgageCredit
     */
    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.wsdl.DocRsType> getLeadsMortgageCreditInquiryResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.wsdl.DocRsType> handler = (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseMessage = response.getResMessage();
            final String responseCode = response.getResCode();
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0010_SUCCESS:
                        break;

                    case BackendRespCode.R0010_FAILED_INVALID_INPUT:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_INVALID_INPUT_ERROR_CODE,
                                ErrorMessages.SOAP_MORTGAGE_INVALID_INPUT_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_INVALID_INPUT_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0010_FAILED_PENDING_CA_FOUND:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_PENDING_CA_FOUND_CODE,
                                ErrorMessages.SOAP_MORTGAGE_PENDING_CA_FOUND_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_PENDING_CA_FOUND_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0010_FAILED_PROCESS_FAILURE:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_PROCESS_FAILURE_CODE,
                                ErrorMessages.SOAP_MORTGAGE_PROCESS_FAILURE_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_PROCESS_FAILURE_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0010_FAILED_WORK_IN_PROGRESS:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_WORK_IN_PROGRESS_CODE,
                                ErrorMessages.SOAP_MORTGAGE_WORK_IN_PROGRESS_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_WORK_IN_PROGRESS_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0010_FAILED_CRITERIA_NOT_MET:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_CRITERIA_NOT_MET_CODE,
                                ErrorMessages.SOAP_MORTGAGE_CRITERIA_NOT_MET_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_CRITERIA_NOT_MET_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    default:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_OTHER_ERROR_CODE,
                                ErrorMessages.SOAP_MORTGAGE_OTHER_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_OTHER_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                }
            }
        };
        return handler;
    }

    /**
     * Pretty much the same as inquireMortgageCredit (R0010)
     *
     * @return ExceptionHandler for DocRsType for inquireMortgageLimit
     */
    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.wsdl.DocRsType> getLeadsMortgageLimitInquiryResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.wsdl.DocRsType> handler = (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseMessage = response.getResMessage();
            final String responseCode = response.getResCode();
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0011_SUCCESS:
                        break;

                    case BackendRespCode.R0011_FAILED_INVALID_INPUT:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_INVALID_INPUT_ERROR_CODE,
                                ErrorMessages.SOAP_MORTGAGE_INVALID_INPUT_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_INVALID_INPUT_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0011_FAILED_PENDING_CA_FOUND:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_PENDING_CA_FOUND_CODE,
                                ErrorMessages.SOAP_MORTGAGE_PENDING_CA_FOUND_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_PENDING_CA_FOUND_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0011_FAILED_PROCESS_FAILURE:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_PROCESS_FAILURE_CODE,
                                ErrorMessages.SOAP_MORTGAGE_PROCESS_FAILURE_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_PROCESS_FAILURE_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0011_FAILED_WORK_IN_PROGRESS:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_WORK_IN_PROGRESS_CODE,
                                ErrorMessages.SOAP_MORTGAGE_WORK_IN_PROGRESS_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_WORK_IN_PROGRESS_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0011_FAILED_CRITERIA_NOT_MET:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_CRITERIA_NOT_MET_CODE,
                                ErrorMessages.SOAP_MORTGAGE_CRITERIA_NOT_MET_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_CRITERIA_NOT_MET_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    default:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_MORTGAGE_OTHER_ERROR_CODE,
                                ErrorMessages.SOAP_MORTGAGE_OTHER_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_MORTGAGE_OTHER_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                }
            }
        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.wsdl.DocRsType> getMortgageSubmissionLeadsResponseHanlder() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.wsdl.DocRsType> handler = (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMessage();
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0020_FAILED_INVALID_INPUT:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_CODE,
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_PENDING_CA_FOUND:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_CODE,
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_PROCESS_FAILURE:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_CODE,
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_WORK_IN_PROGRESS:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_CODE,
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_CRITERIA_NOT_MET:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_CODE,
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    default:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_CODE,
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                }
            }
        };

        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.auto.wsdl.DocRsType> getAutoLeadResponseHandler() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.auto.wsdl.DocRsType> handler = (response) -> {
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMsg();
            switch (responseCode) {
                case BackendRespCode.LEAD_AUTO_SUCCESS:
                    break;
                default:
                    throw new ResourceConflictException(
                            ErrorMessages.LEAD_INQUIRY_MAXIMUIM_CREDIT_LIMIT_CODE,
                            ErrorMessages.LEAD_INQUIRY_MAXIMUIM_CREDIT_LIMIT_MESSAGE,
                            ErrorInfo.SeverityLevel.ERROR,
                            String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, responseCode, responseMessage),
                            null,
                            responseCode,
                            responseMessage
                    );
            }

        };
        return handler;
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.loan.wsdl.DocRsType> getBusinessLoanLeadsResponseHandler() {
        return (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMessage();
            String code = ErrorMessages.SOAP_R0033_BUSINESS_RULE_ERROR_CODE;
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0020_FAILED_INVALID_INPUT:
                        code = ErrorMessages.SOAP_R0033_INVALID_INPUT_ERROR_CODE;
                        break;
                    case BackendRespCode.R0020_FAILED_PENDING_CA_FOUND:
                    case BackendRespCode.R0020_FAILED_PROCESS_FAILURE:
                    case BackendRespCode.R0020_FAILED_WORK_IN_PROGRESS:
                    case BackendRespCode.R0020_FAILED_CRITERIA_NOT_MET:
                        code = ErrorMessages.SOAP_R0033_BUSINESS_RULE_ERROR_CODE;
                }
                throw new ResourceConflictException(
                        code,
                        ErrorMessages.SOAP_R0033_INVALID_INPUT_ERROR_MESSAGE,
                        ErrorInfo.SeverityLevel.ERROR,
                        responseCode + " " + responseMessage,
                        "",
                        errorCode,
                        responseMessage
                );
            }
        };
    }

    public static ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.v2.submission.wsdl.DocRsType> getMortgageSubmissionLeadsResponseHanlderV2() {
        ExceptionHandler<com.scb.api.ent.customer.services.origination.domain.mortgage.v2.submission.wsdl.DocRsType> handler = (response) -> {
            final String errorCode = response.getErrorCode();
            final String responseCode = response.getResCode();
            final String responseMessage = response.getResMessage();
            if (responseCode.equalsIgnoreCase(BackendRespCode.FAILED)) {
                switch (errorCode) {
                    case BackendRespCode.R0020_FAILED_INVALID_INPUT:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_CODE,
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_INVALID_INPUT_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_PENDING_CA_FOUND:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_CODE,
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_PENDING_CA_FOUND_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_PROCESS_FAILURE:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_CODE,
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_PROCESS_FAILURE_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_WORK_IN_PROGRESS:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_CODE,
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_WORK_IN_PROGRESS_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    case BackendRespCode.R0020_FAILED_CRITERIA_NOT_MET:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_CODE,
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_CRITERIA_NOT_MET_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                    default:
                        throw new ResourceConflictException(
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_CODE,
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_MESSAGE,
                                ErrorInfo.SeverityLevel.ERROR,
                                ErrorMessages.SOAP_R0020_OTHER_ERROR_MESSAGE,
                                String.format(ErrorMessages.BUSINESS_ERROR_MOREINFO_NEW_STANDARD, errorCode, responseMessage),
                                errorCode,
                                responseMessage
                        );
                }
            }
        };

        return handler;
    }
}
