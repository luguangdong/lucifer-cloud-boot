package com.lucifer.cloud.boot.blog.config;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.lucifer.cloud.auth.constant.SecurityConstants;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

/**
 * @author lucifer
 * @date 2023/12/23 22:46
 */
@Component
public class UserSystem {

    @Resource
    private UserMapper userMapper;

    public  String token(HttpServletRequest request){
        Enumeration<String> authorizations = request.getHeaders("Authorization");
        String token = null;
        while (authorizations.hasMoreElements()) {
            String element  = authorizations.nextElement();
            String[] split = element.split("Bearer ");
            token = split[1];
        }
        return token;
    }


    public Long userId(HttpServletRequest request){
        String token = token(request);
        JWT jwt = JWTUtil.parseToken(token);
        JSONObject claimsJson = jwt.getPayload().getClaimsJson();
        String uniqueId = null;
        if(claimsJson.get(SecurityConstants.TOKEN_UNIQUE_ID) instanceof NumberWithFormat){
            NumberWithFormat numberWithFormat = (NumberWithFormat) claimsJson.get(SecurityConstants.TOKEN_UNIQUE_ID);
            uniqueId =  String.valueOf( numberWithFormat.intValue());
        }else {
            uniqueId = (String) claimsJson.get(SecurityConstants.TOKEN_UNIQUE_ID);
        }
        return Long.parseLong(uniqueId);
    }

    public User user(HttpServletRequest request){
        return userMapper.selectById(userId(request));
    }

}
