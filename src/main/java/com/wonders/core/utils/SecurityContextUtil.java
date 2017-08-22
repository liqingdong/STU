package com.wonders.core.utils;

import com.wonders.core.entity.User;
import com.wonders.web.security.MyUserDetail;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {

    public static User getCurrentUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        MyUserDetail detail = (MyUserDetail) ctx.getAuthentication().getPrincipal();
        return detail.getUser();
    }

    public static String getUsername() {
        User user = getCurrentUser();
        if (user == null) return null;
        return user.getUsername();
    }

}