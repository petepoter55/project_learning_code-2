package com.project.market.impl.service.jwt;

import com.project.market.constant.Constant;
import com.project.market.dto.req.register.RegisterDtoRequest;
import com.project.market.dto.res.JwtDtoResponse;
import com.project.market.impl.exception.ResponseException;
import com.project.market.util.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

@Service
public class JwtImpl {
    private static final Logger logger = Logger.getLogger(JwtImpl.class);

    @Value("${jwt.secretkey}")
    private String jwtSecretkey;

    public String generateToken(RegisterDtoRequest registerDtoRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Calendar currentDate = Calendar.getInstance();
        Date date = currentDate.getTime();

        SecretKey key = Keys.hmacShaKeyFor(this.jwtSecretkey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("username", registerDtoRequest.getUsername())
                .claim("email", registerDtoRequest.getEmail())
                .claim("referenceNo", registerDtoRequest.getRefNoInformation())
                .claim("password", new Util().hashSha256(registerDtoRequest.getPassword()))
                .claim("telephone", registerDtoRequest.getTelephone())
                .setIssuedAt(date)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public JwtDtoResponse getDataJwt(String jwtToken) {
        JwtDtoResponse jwtResponse = new JwtDtoResponse();
        try {
            SecretKey key = Keys.hmacShaKeyFor(this.jwtSecretkey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);

            jwtResponse.setUsername(jws.getBody().get("username").toString());
            jwtResponse.setEmail(jws.getBody().get("email").toString());
            jwtResponse.setReferenceNo(jws.getBody().get("referenceNo").toString());
            jwtResponse.setPassword(jws.getBody().get("password").toString());
            jwtResponse.setTelephone(jws.getBody().get("telephone").toString());
            jwtResponse.setIssueDate(jws.getBody().getIssuedAt());
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
        return jwtResponse;
    }
}
