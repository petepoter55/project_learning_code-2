package com.project.market.impl.service.formManagement;

import com.project.market.constant.Constant;
import com.project.market.dto.req.information.EditFormManagementRequest;
import com.project.market.dto.res.Response;
import com.project.market.entity.form.FormManagementUpload;
import com.project.market.entity.repository.form.FormManagementRepository;
import com.project.market.entity.repository.form.FormManagementUploadRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@Service
public class FormManagement {
    private static final Logger logger = Logger.getLogger(FormManagement.class);

    @Autowired
    private FormManagementRepository formManagementRepository;
    @Autowired
    private FormManagementUploadRepository formManagementUploadRepository;

    public Response insertFormMangeUpload(MultipartFile[] file, EditFormManagementRequest editFormManagementRequest){
        String process = "";

        try {
            //set minTime(00:00:00) / maxTime(23:59:59)
            editFormManagementRequest.setStartDate(new DateUtil().getDaysTimes(editFormManagementRequest.getStartDate(), false));
            editFormManagementRequest.setEndDate(new DateUtil().getDaysTimes(editFormManagementRequest.getStartDate(), true));

            Optional<FormManagementUpload> formManagement = formManagementUploadRepository.findById(editFormManagementRequest.getId());


        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
        return null;
    }

    public void insertFormManagementUpload(MultipartFile[] file){
        try {
            Integer seq = new Integer("1");
            for(MultipartFile f : file){
                FormManagementUpload formManagementUpload = new FormManagementUpload();
                formManagementUpload.setFile(f.getBytes());
                formManagementUpload.setFileName(f.getOriginalFilename());
                formManagementUpload.setSeq(seq++);
                formManagementUpload.setFileContent(f.getContentType());
                formManagementUpload.setCreateDateTime(new DateUtil().getFormatsDateMilli());
                
                formManagementUploadRepository.save(formManagementUpload);
            }

        }catch (ResponseException | IOException | ParseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
    }

    public void editFormManagementUpload(MultipartFile[] file,FormManagementUpload formManagementUpload){
        try {
            Integer seq = new Integer("1");
            for(MultipartFile f : file){
                formManagementUpload.setFile(f.getBytes());
                formManagementUpload.setFileName(f.getOriginalFilename());
                formManagementUpload.setSeq(seq++);
                formManagementUpload.setFileContent(f.getContentType());
                formManagementUpload.setCreateDateTime(new DateUtil().getFormatsDateMilli());

                formManagementUploadRepository.save(formManagementUpload);
            }
        }catch (ResponseException | IOException | ParseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
    }
}
