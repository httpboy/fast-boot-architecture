package com.authorized.thirdpartylogin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Classname ThirdPartyLoginServiceImpl
 * @Created by whf
 */
@Service
public class ThirdPartyLoginServiceImpl implements ThirdPartyLoginService {

    @Override
    public String getAuthorize(String source) {
        BaseLoginProcessor loginProcessor = BaseLoginProcessor.loginServiceMap.get(source);
        return loginProcessor.getAuthorize();
    }

    @Override
    public JSONObject getAccessToken(String source, ThirdPartyLoginDto thirdPartyLoginDto) {
        BaseLoginProcessor loginProcessor = BaseLoginProcessor.loginServiceMap.get(source);
        return loginProcessor.getAccessToken(thirdPartyLoginDto);
    }

    @Override
    public JSONObject getUserInfo(String source, ThirdPartyLoginDto thirdPartyLoginDto) {
        BaseLoginProcessor loginProcessor = BaseLoginProcessor.loginServiceMap.get(source);
        return loginProcessor.getUserInfo(thirdPartyLoginDto);
    }
}
