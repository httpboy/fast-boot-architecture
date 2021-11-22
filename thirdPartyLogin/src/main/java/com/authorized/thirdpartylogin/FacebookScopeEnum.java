package com.authorized.thirdpartylogin;

/**
 * @Classname FacebookScopeEnum
 * @Created by whf
 */
public enum FacebookScopeEnum {

    EMAIL("email", "获取用户的邮箱", false),
    USER_AGE_RANGE("user_age_range", "允许应用程序访问用户的年龄范围", false),
    USER_BIRTHDAY("user_birthday", "获取用户的生日", false),
    USER_FRIENDS("user_friends", "获取用户的好友列表", false),
    USER_GENDER("user_gender", "获取用户的性别", false),
    USER_HOMETOWN("user_hometown", "获取用户的家乡信息", false),
    USER_LIKES("user_likes", "获取用户的喜欢列表", false),
    USER_LINK("user_link", "获取用户的个人链接", false),
    USER_LOCATION("user_location", "获取用户的位置信息", false),
    USER_PHOTOS("user_photos", "获取用户的相册信息", false),
    USER_POSTS("user_posts", "获取用户发布的内容", false),
    USER_VIDEOS("user_videos", "获取用户上传的视频信息", false),
    GROUPS_ACCESS_MEMBER_INFO("groups_access_member_info", "获取公开的群组成员信息", false),
    PUBLISH_TO_GROUPS("publish_to_groups", "授权您的应用程序代表某人将内容发布到组中，前提是他们已经授予您的应用程序访问权限", false);

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

    FacebookScopeEnum(String scope, String description, boolean isDefault) {
        this.scope = scope;
        this.description = description;
        this.isDefault = isDefault;
    }
}
