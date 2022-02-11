package com.jam.handler;

import com.jam.handler.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringbootStudy
 * @description: 自定义用户踢出后的执行方法
 * @author: Mr.Pu
 * @create: 2022-01-21 17:10
 **/
@Slf4j
public class AdminSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        log.debug("用户被踢出");
        Result<Object> result = new Result<>();
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result.resultData(405,"用户被踢出"));
    }
}
