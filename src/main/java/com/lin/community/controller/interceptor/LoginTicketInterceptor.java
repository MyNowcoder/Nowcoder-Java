package com.lin.community.controller.interceptor;

import com.lin.community.pojo.LoginTicket;
import com.lin.community.pojo.User;
import com.lin.community.service.UserService;
import com.lin.community.utils.CookieUtil;
import com.lin.community.utils.HostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 每次请求都会进入拦截器，在同一个线程中get/set ThreadLocal
 * @param
 * @return
 *
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    HostUser hostUser;
    /**
     * 在进入Controller方法之前通过cookie查询登录凭证，再通过有效的登录凭证查询user,并且通过ThreadLocal Set进去
     * @param   [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object]
     * @return  boolean
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取登录凭证
        String cookie = CookieUtil.getCookie(request, "ticket");
        if(cookie!=null){
            //查询登录凭证
            LoginTicket loginTicketByTicket = userService.getLoginTicketByTicket(cookie);
            //如果凭证有效
            if(loginTicketByTicket!=null&&loginTicketByTicket.getStatus()==0&&loginTicketByTicket.getExpired().after(new Date()))
            {
                //根据凭证获取user
                User user = userService.getUserById(loginTicketByTicket.getUserId());
                //由于每个隔离线程在不同方法中都要对各自的user对象进行访问，为了避免参数传递，所以使用ThreadLocal
                hostUser.setUser(user);
            }
        }
        return true;
    }

    /**
     * 在Controller方法执行后，模板解析前执行，将user存入模板 并且通过ThreadLocal Get出来
     * @param   [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView]
     * @return  void
     *
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostUser.getUser();
        if(user!=null&&modelAndView!=null)
            modelAndView.addObject("user", user);
    }

    /**
     * 解析完模板后执行，把存入的user清理
     * @param   [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception]
     * @return  void
     *
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostUser.clear();
    }
}
