package com.authorized.thirdpartylogin;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Oauth2LoginProcessor
 * @Created by whf
 */
public class Oauth2LoginProcessor extends BaseLoginProcessor {
    public String authorizeUrl;
    public String accessTokenUrl;
    public String userInfoUrl;
    public String appId;
    public String appKey;
    public String redirectUri;
    public String scope;

    protected Oauth2LoginProcessor(String thirdPartyType) {
        super(thirdPartyType);
    }

    @Override
    public String getAuthorize() {
        return UrlBuilder.of(authorizeUrl, StandardCharsets.UTF_8)
                .addQuery("response_type", "code")
                .addQuery("client_id", appId)
                .addQuery("scope", scope)
                .addQuery("redirect_uri", redirectUri).build();
    }

    @Override
    public JSONObject getAccessToken(ThirdPartyLoginDto thirdPartyLoginDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", thirdPartyLoginDto.getCode());
        params.put("client_id", appId);
        params.put("client_secret", appKey);
        params.put("redirect_uri", redirectUri);
        params.put("grant_type", "authorization_code");
        String result = HttpUtil.post(accessTokenUrl, params);
        return JSONObject.parseObject(result);
    }

    @Override
    public JSONObject getUserInfo(ThirdPartyLoginDto thirdPartyLoginDto) {
        String url = UrlBuilder.of(userInfoUrl, StandardCharsets.UTF_8)
                .addQuery("access_token", thirdPartyLoginDto.getAccessToken()).build();
        String userInfo = HttpUtil.get(url);
        return JSONObject.parseObject(userInfo);
    }
}
