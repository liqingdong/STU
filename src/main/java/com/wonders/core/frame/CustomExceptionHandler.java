package com.wonders.core.frame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonders.core.utils.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler extends SimpleMappingExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LogUtil.getLogger(CustomExceptionHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        logger.error(getLogByRequest(request) + ex.getMessage(), ex);
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            ResponseBody body = method.getMethodAnnotation(ResponseBody.class);

            //  判断有没有@ResponseBody的注解没有的话调用父方法  
            if (body != null) {
                // 设置状态码
                response.setStatus(HttpStatus.OK.value());
                // 设置返回值
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                // 返回值编码
                response.setCharacterEncoding("UTF-8");

                CommonResult<?> result = parseException(ex);

                writeCommonResult(response, result);
            }
        } else {
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                // ajax 请求
                CommonResult<?> result = parseException(ex);

                writeCommonResult(response, result);
            }
        }

        return null;
    }

    private CommonResult<?> parseException(Exception ex) {
        CommonResult<?> result = new CommonResult<>();
        result.setSuccess(false);
        if (ex instanceof BizException) {
            BizException bizex = (BizException) ex;
            result.setMessage(bizex.getMessage());
        } else if (ex instanceof HttpStatusCodeException) {
            HttpStatusCodeException e = (HttpStatusCodeException) ex;
            try {
                @SuppressWarnings("unchecked")
                Map<String, String> map = objectMapper.readValue(new String(e.getResponseBodyAsByteArray(), "UTF-8"), Map.class);
                result.setMessage(map.get("message"));
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                result.setMessage(e1.getMessage());
            }
        } else {
            if (StringUtils.isBlank(ex.getMessage())) {
                logger.error(ex.getMessage(), ex);
                result.setMessage("服务器发生未知异常");
            } else {
                logger.error(ex.getMessage(), ex);
                result.setMessage(ex.getMessage());
            }
        }
        return result;
    }

    private void writeCommonResult(HttpServletResponse response, CommonResult<?> result) {
        try {
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(result));
            writer.close();
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * 将请求转为日志记录格式
     *
     * @param request request
     * @return String
     */
    @SuppressWarnings("unchecked")
    private String getLogByRequest(HttpServletRequest request) {
        if (request == null)
            return "";
        StringBuilder sb = new StringBuilder();
        Enumeration<String> enumParams = request.getParameterNames();
        sb.append("\n[Param area start]\n");
        while (enumParams.hasMoreElements()) {
            String paramName = enumParams.nextElement();

            String[] arrayValues = request.getParameterValues(paramName);
            if (arrayValues == null)
                continue;
            for (int i = 0; i < arrayValues.length; i++) {
                sb.append("[param]").append(paramName).append("=")
                        .append(arrayValues[i] == null ? "" : arrayValues[i] + "\n");
            }
        }
        sb.append("[Param area end]\n");
        return sb.toString();
    }

}
