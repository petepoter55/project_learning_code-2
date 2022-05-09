package com.project.market.impl.validation.custom;

import com.project.market.constant.Constant;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.validation.ValidationAbstract;
import com.project.market.model.TemplateKeeper;
import org.apache.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorSchema extends ValidationAbstract {
    private static final Logger logger = Logger.getLogger(ValidatorSchema.class);
    @Autowired
    private TemplateKeeper templateKeeper;

    private String getSchemaConfigFactory(String requestName, String pathSchemaRequest){
        String schemaConfigFactory = "";
        try {
            schemaConfigFactory = templateKeeper.getTemplate(requestName, pathSchemaRequest);
        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
        return schemaConfigFactory;
    }
    @Override
    public void validate(String requestName, String jsonRequest) throws ResponseException {
        logger.info("serviceNameSchema :" + String.format(Constant.TEMPLATE_PATH_VALIDATION_FILE,requestName));
        try {
            String schemaString = getSchemaConfigFactory(requestName, String.format(Constant.TEMPLATE_PATH_VALIDATION_FILE,requestName));
            logger.info("schemaString :" + schemaString);
            JSONObject jsonSchema = new JSONObject(cleanUpUnwantedSpaces(schemaString));
            JSONObject jsonSubject = new JSONObject(cleanUpUnwantedSpaces(jsonRequest));
            logger.info("jsonSchema :" + jsonSchema);
            logger.info("jsonSubject :" + jsonSubject);

            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        }catch (ResponseException ex){
            logger.error(String.format(Constant.THROW_EXCEPTION,ex.getMessage()));
            throw new ResponseException(Constant.STATUS_CODE_FAIL,String.format(Constant.THROW_EXCEPTION,ex.getMessage()));
        }
    }

    private String cleanUpUnwantedSpaces(String jsonString) {
        return jsonString.replaceAll("\\t*", "");
    }
}
