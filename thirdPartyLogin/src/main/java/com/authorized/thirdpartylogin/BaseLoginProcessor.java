package com.authorized.thirdpartylogin;


import java.util.HashMap;

/**
 * @Classname BaseLoginProcessor
 * @Created by whf
 */
public abstract class BaseLoginProcessor extends AbstractLoginProcessor {

    public static HashMap<String, BaseLoginProcessor> loginServiceMap = new HashMap<>();

    protected BaseLoginProcessor(String thirdPartyType) {
        loginServiceMap.put(thirdPartyType, this);
    }
}
