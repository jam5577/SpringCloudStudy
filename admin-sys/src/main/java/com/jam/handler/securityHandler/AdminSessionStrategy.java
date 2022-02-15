package com.jam.handler.securityHandler;

import com.jam.enums.ExceptionType;
import com.jam.handler.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringbootStudy
 * @description: 自定义用户踢出后的执行方法
 * @author: Mr.Pu
 * @create: 2022-01-21 17:10
 **/
@Slf4j
@Component
public class AdminSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        log.debug("用户被踢出");
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(Result.error(ExceptionType.USER_KICKED_OUT));
    }
}
