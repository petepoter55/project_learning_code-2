package com.project.market.impl.service.register;

import com.project.market.config.email.MailBuilder;
import com.project.market.constant.Constant;
import com.project.market.dto.req.email.EmailDtoRequest;
import com.project.market.dto.req.register.ChangePasswordDtoRequest;
import com.project.market.dto.req.register.LoginDtoRequest;
import com.project.market.dto.req.register.RegisterDtoRequest;
import com.project.market.dto.res.JwtDtoResponse;
import com.project.market.dto.res.Response;
import com.project.market.entity.register.User;
import com.project.market.impl.service.email.EmailImpl;
import com.project.market.impl.validation.validate.ValidateRequestRegister;
import com.project.market.repository.information.InformationMarketRepository;
import com.project.market.repository.register.UserRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.service.jwt.JwtImpl;
import com.project.market.util.DateUtil;
import com.project.market.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@Service
public class RegisterImpl {
    private static final Logger logger = Logger.getLogger(RegisterImpl.class);

    @Autowired
    private InformationMarketRepository informationMarketRepository;
    @Autowired
    private JwtImpl jwtImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailImpl emailImpl;
    @Autowired
    private ValidateRequestRegister validateRequestRegister;

    public ResponseEntity<Response> register(RegisterDtoRequest registerDtoRequest) {
        logger.info("------------ register start ------------");
        logger.info("request register : " + registerDtoRequest.toString());
        User user = new User();
        try {
            validateRequestRegister.validateRequest(registerDtoRequest);

            User userDup = userRepository.findByUsername(registerDtoRequest.getUsername());
            if (userDup != null) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_CREATE_ACCOUNT_DUP);
            }

            user.setUsername(registerDtoRequest.getUsername());
            user.setJwt_data(jwtImpl.generateToken(registerDtoRequest));
            user.setAuthenticate(Constant.AUTHENTICATE_TYPE_NORMAL);
            user.setEmail(registerDtoRequest.getEmail());
            user.setFirstName(registerDtoRequest.getFirstName());
            user.setLastName(registerDtoRequest.getLastName());
            user.setDelFlag(registerDtoRequest.isDelFlag());
            user.setCreateDateTime(new DateUtil().getFormatsDateMilli());
            userRepository.save(user);

            logger.info("send Email..");
            final EmailDtoRequest mail = new MailBuilder()
                    .From("boonyaris.pen@gmail.com")
                    .To(registerDtoRequest.getEmail())
                    .Template("email-template/register-success.html")
                    .AddContext("subject", "Thank! " + registerDtoRequest.getUsername())
                    .AddContext("content", "Thank you for filling out our sign up form. We are glad that you joined us")
                    .Subject("Thank For Register!")
                    .createMail();
            ResponseEntity<String> resSendEmail = this.emailImpl.sendEmail(mail);
            logger.info("response send Email : " + resSendEmail.getBody());

        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return new ResponseEntity<>(Response.fail(e.getExceptionCode(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | ParseException ex) {
            logger.error(String.format(Constant.THROW_EXCEPTION, ex.getMessage()));
            return new ResponseEntity<>(Response.fail(String.valueOf(ex.hashCode()), ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        logger.info("------------ register done ------------");
        return new ResponseEntity<>(Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, user), HttpStatus.OK);
    }

    public JwtDtoResponse getDataToken(String jwtToken) {
        JwtDtoResponse jwtDtoResponse = new JwtDtoResponse();
        try {
            jwtDtoResponse = jwtImpl.getDataJwt(jwtToken);
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }

        return jwtDtoResponse;
    }

    public Response changePassword(ChangePasswordDtoRequest changePasswordDtoRequest) {
        Util util = new Util();
        try {
            User user = userRepository.findByUsername(changePasswordDtoRequest.getUsername());
            if (user == null) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_REGISTER_CHECKDATA_FOUND);
            }

            JwtDtoResponse jwtDtoResponse = jwtImpl.getDataJwt(user.getJwt_data());
            if (jwtDtoResponse == null) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_DATA_TOKEN_FOUND);
            }

            boolean checkOldPassword = util.checkOldPassword(jwtDtoResponse.getPassword(), util.hashSha256(changePasswordDtoRequest.getOldPassword()));
            if (!checkOldPassword) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_DATA_PASSWORD);
            }

            RegisterDtoRequest registerDtoRequest = new RegisterDtoRequest();
            registerDtoRequest.setUsername(jwtDtoResponse.getUsername());
            registerDtoRequest.setPassword(changePasswordDtoRequest.getNewPassword());
            registerDtoRequest.setRefNoInformation(jwtDtoResponse.getReferenceNo());
            registerDtoRequest.setEmail(jwtDtoResponse.getEmail());
            registerDtoRequest.setTelephone(jwtDtoResponse.getTelephone());

            user.setJwt_data(jwtImpl.generateToken(registerDtoRequest));
            user.setUpdateDateTime(new DateUtil().getFormatsDateMilli());
            userRepository.save(user);
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | ParseException ex) {
            return Response.fail(String.valueOf(ex.hashCode()), ex.getMessage(), null);
        }

        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }

    public Response login(LoginDtoRequest loginDtoRequest) {
        User user;
        try {
            user = userRepository.findByUsername(loginDtoRequest.getUsername());
            if (user == null) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_DATA_LOGIN_USERNAME);
            }

            JwtDtoResponse jwtDtoResponse = jwtImpl.getDataJwt(user.getJwt_data());
            if (jwtDtoResponse == null) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_DATA_TOKEN_FOUND);
            }

            boolean checkPassword = new Util().checkPassphrases(jwtDtoResponse.getPassword(), loginDtoRequest.getPassword());
            if (!checkPassword) {
                throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_DATA_LOGIN_PASSWORD);
            }

        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            return Response.fail(String.valueOf(ex.hashCode()), ex.getMessage(), null);
        }

        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }
}
