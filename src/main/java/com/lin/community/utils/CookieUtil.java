package com.lin.community.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieUtil {
    public static String getCookie(HttpServletRequest request,String target)
    {
        if(request==null||target==null)
                return null;
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
                return null;
        for(Cookie cookie:cookies)
        {
            if(cookie.getName().equals(target))
                return cookie.getValue();
        }
        return null;
    }
}
