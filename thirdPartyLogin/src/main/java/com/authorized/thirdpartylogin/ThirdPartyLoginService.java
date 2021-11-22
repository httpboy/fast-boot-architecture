package com.authorized.thirdpartylogin;

import com.alibaba.fastjson.JSONObject;

/**
 * @Classname ThirdPartyLoginService
 * @Created by whf
 */
public interface ThirdPartyLoginService {

    String getAuthorize(String source);

    JSONObject getAccessToken(String source, ThirdPartyLoginDto thirdPartyLoginDto);

    JSONObject getUserInfo(String source, ThirdPartyLoginDto thirdPartyLoginDto);
}
