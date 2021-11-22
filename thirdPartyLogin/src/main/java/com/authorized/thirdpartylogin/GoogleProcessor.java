package com.authorized.thirdpartylogin;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Classname GoogleProcessor
 * @Created by whf
 */
@Component
public class GoogleProcessor extends Oauth2LoginProcessor implements InitializingBean {

    @Autowired
    private Environment environment;

    public GoogleProcessor() {
        super(ThirdPartyEnum.GOOGLE.getCode());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.authorizeUrl = environment.getProperty("third.login.google.authorize-url");
        this.accessTokenUrl = environment.getProperty("third.login.google.access-token-url");
        this.userInfoUrl = environment.getProperty("third.login.google.user-info-url");
        this.appId = environment.getProperty("third.login.google.app-id");
        this.appKey = environment.getProperty("third.login.google.app-key");
        this.redirectUri = environment.getProperty("third.login.google.redirect-url");
        this.scope = StrUtil.join(" ", Arrays.stream(GoogleScopeEnum.values()).map(GoogleScopeEnum::getScope).collect(Collectors.toList()));
    }
}
