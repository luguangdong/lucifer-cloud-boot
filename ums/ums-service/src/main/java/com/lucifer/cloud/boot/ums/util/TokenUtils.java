package com.lucifer.cloud.boot.ums.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;

import java.util.Objects;

/**
 * @author lucifer
 * @date 2023/12/23 22:46
 */
public class TokenUtils {

    public static String getToken(RequestEntity request){
        HttpHeaders headers = request.getHeaders();
        String token = null;
        if(Objects.nonNull(headers.get("Authorization"))){
            String authorization = headers.get("Authorization").get(0);
            String[] split = authorization.split("Bearer ");
            token = split[1];
        }
        return token;
    }
}
