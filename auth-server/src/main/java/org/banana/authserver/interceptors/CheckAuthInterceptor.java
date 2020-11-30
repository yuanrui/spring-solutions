package org.banana.authserver.interceptors;

import org.banana.authserver.annotation.AllowAnonymous;
import org.banana.authserver.annotation.AllowAuth;
import org.banana.authserver.domain.UserModel;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author yuanrui@live.cn
 * @since 2020/11/30 15:02
 */
public class CheckAuthInterceptor extends HandlerInterceptorAdapter {
    final static String USER_SESSION = "USER_SESSION";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AllowAuth allowAuth = method.getAnnotation(AllowAuth.class);
        AllowAnonymous allowAnonymous = method.getAnnotation(AllowAnonymous.class);

        System.out.println("AllowAnonymous:" + (allowAnonymous != null) + " AllowAuth:" + (allowAuth != null));
        HttpSession session = request.getSession();
        UserModel userModel = (UserModel) session.getAttribute(USER_SESSION);

        if(allowAnonymous != null){
            return true;
        }

        if(allowAuth != null && userModel != null){
            return true;
        }

        if (userModel == null) {
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
