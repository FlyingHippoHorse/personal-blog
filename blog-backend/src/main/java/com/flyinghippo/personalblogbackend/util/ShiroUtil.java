package com.flyinghippo.personalblogbackend.util;

import com.flyinghippo.personalblogbackend.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}