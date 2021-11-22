package com.authorized.thirdpartylogin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ThirdPartyLoginController
 * @Created by whf
 */
@RestController
@RequestMapping("/oauth")
public class ThirdPartyLoginController {

    @Autowired
    private ThirdPartyLoginService thirdPartyLoginService;

    @GetMapping("/{source}/login")
    public String getLoginUrl(@PathVariable("source") String source) {
        return thirdPartyLoginService.getAuthorize(source);
    }

    @GetMapping("/{source}/render")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        response.sendRedirect(getLoginUrl(source));
    }

    @GetMapping("/{source}/callback")
    public Object callback(@PathVariable("source") String source, ThirdPartyLoginDto thirdPartyLoginDto, HttpServletRequest request) {
        JSONObject result = thirdPartyLoginService.getAccessToken(source, thirdPartyLoginDto);
        String token = result.getString("access_token");
        thirdPartyLoginDto.setAccessToken(token);
        return thirdPartyLoginService.getUserInfo(source, thirdPartyLoginDto);
    }

    @PostMapping("/{source}/user")
    public Object getUser(@PathVariable("source") String source, @RequestBody ThirdPartyLoginDto thirdPartyLoginDto) {
        return thirdPartyLoginService.getUserInfo(source, thirdPartyLoginDto);
    }
}
