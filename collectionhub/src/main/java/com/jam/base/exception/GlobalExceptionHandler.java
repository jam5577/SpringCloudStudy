package com.jam.base.exception;

import com.jam.base.result.Result;
import com.jam.base.result.ResultInfo;
import com.jam.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 异常处理类
 * @author: Mr.Pu
 * @create: 2022-02-16 17:05
 **/
@Slf4j
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if(e instanceof IllegalArgumentException || e instanceof IllegalStateException){
            log.error(e.getMessage());
        }else {
            log.error(e.getMessage(),e);
        }
        //处理ajax请求
        String header = httpServletRequest.getHeader("X-Requested-With");
        if (header.equals("XMLHttpRequest")){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            try {
                httpServletResponse.getWriter().print(JSONUtils.toJSON(Result.error(e.getMessage())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else {
            httpServletRequest.setAttribute("message", ResultInfo.INNER_ERROR);
        }
        return new ModelAndView("error");
    }
}
