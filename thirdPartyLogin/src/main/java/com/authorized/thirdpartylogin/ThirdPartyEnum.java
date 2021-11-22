package com.authorized.thirdpartylogin;

/**
 * @Classname ThirdPartyEnum
 * @Created by whf
 */
public enum ThirdPartyEnum {
    FACEBOOK("facebook"),
    GOOGLE("google");

    private String code;

    public String getCode() {
        return code;
    }

    ThirdPartyEnum(String code) {
        this.code = code;
    }
}
