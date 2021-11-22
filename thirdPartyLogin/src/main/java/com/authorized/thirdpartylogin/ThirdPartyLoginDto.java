package com.authorized.thirdpartylogin;

import lombok.Data;

import java.io.Serializable;

/**
 * @Created by whf
 */
@Data
public class ThirdPartyLoginDto implements Serializable {
    private String code;
    private String accessToken;
}
