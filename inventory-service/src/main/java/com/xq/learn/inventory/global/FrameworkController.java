package com.xq.learn.inventory.global;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 这里捕获SpringMVC框架异常，当请求执行过程中抛出了未捕获的异常，都会触发错误页面
 * /error请求的方法跟本地调用的方法类型相同，即如果抛出异常的请求是POST，调用/error的请求任然是POST
 * 该功能有servlet提供，只要配置了错误页面，当有未捕获的异常抛出时，servlet都会将本次请求转发到/error,
 * Dispatcher会再次分发本地请求到当/error对应的Controller
 * 该页面被调用的前提条件是Dispatcher分发请求时抛出了异常或者请求404，因此如果DispatcherServlet中抛出的异常都
 * 被ExceptionHandler捕获，然后输出了期望的结果，则不会走当前的Controller。
 * @author xiaoqiang
 * @date 2019/8/14 0:04
 */
@RestController
public class FrameworkController implements ErrorController
{
    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request)
    {
        HttpStatus status = getStatus(request);
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> error = this.errorAttributes.getErrorAttributes(webRequest, true);
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(error, status);
        return responseEntity;
    }

    /**
     * 获取错误状态码
     * @param request request
     * @return httpStatus
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath()
    {
        return ERROR_PATH;
    }
}
