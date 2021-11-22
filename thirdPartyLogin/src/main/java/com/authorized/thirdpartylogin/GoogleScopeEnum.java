package com.authorized.thirdpartylogin;

/**
 * @Classname GoogleScopeEnum
 * @Created by whf
 */
public enum GoogleScopeEnum {
    USER_OPENID("openid", "Associate you with your personal info on Google", true),
    USER_EMAIL("email", "View your email address", true),
    USER_PROFILE("profile", "View your basic profile info", true);

    private String scope;
    private String description;
    private boolean isDefault;

    public String getScope() {
        return this.scope;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    GoogleScopeEnum(String scope, String description, boolean isDefault) {
        this.scope = scope;
        this.description = description;
        this.isDefault = isDefault;
    }
}
