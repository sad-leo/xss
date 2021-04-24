package com.longlysmile.common.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * TODO 错误页面跳转
 *
 * @author wujie
 * @version 1.0
 * @date 2020/11/22 18:22
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {


    @Value("${server.error.path:${error.path:/error}}")
    private String path;

    public CustomErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的JSON响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<String, Object>(16);
        Map<String, Object> originalMsgMap = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        String path = (String) originalMsgMap.get("path");
        String error = (String) originalMsgMap.get("templates/error");
        String message = (String) originalMsgMap.get("message");
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add(path).add(error).add(message);
        map.put("code", "9999");
        map.put("msg", joiner.toString());
        return new ResponseEntity<>(map, status);
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        //请求的状态
        HttpStatus status = getStatus(request);
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        //指定自定义的视图
        return (modelAndView == null ? new ModelAndView("error/4xx.html", model) : modelAndView);
    }
}
