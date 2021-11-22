package com.authorized.thirdpartylogin;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Classname FacebookProcessor
 * @Created by whf
 */
@Component
public class FacebookProcessor extends Oauth2LoginProcessor implements InitializingBean {

    @Autowired
    private Environment environment;

    public FacebookProcessor() {
        super(ThirdPartyEnum.FACEBOOK.getCode());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.authorizeUrl = environment.getProperty("third.login.facebook.authorize-url");
        this.accessTokenUrl = environment.getProperty("third.login.facebook.access-token-url");
        this.userInfoUrl = environment.getProperty("third.login.facebook.user-info-url");
        this.appId = environment.getProperty("third.login.facebook.app-id");
        this.appKey = environment.getProperty("third.login.facebook.app-key");
        this.redirectUri = environment.getProperty("third.login.facebook.redirect-url");
        this.scope = StrUtil.join(" ", Arrays.stream(FacebookScopeEnum.values()).map(FacebookScopeEnum::getScope).collect(Collectors.toList()));
    }
}
