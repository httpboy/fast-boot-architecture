package com.authorized.thirdpartylogin;

import com.alibaba.fastjson.JSONObject;

/**
 * @Classname AbstractLoginProcessor
 * @Created by whf
 */
public abstract class AbstractLoginProcessor {

    public abstract String getAuthorize();

    public abstract JSONObject getAccessToken(ThirdPartyLoginDto thirdPartyLoginDto);

    public abstract JSONObject getUserInfo(ThirdPartyLoginDto thirdPartyLoginDto);
}
