package com.xq.learn.order.global;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.xq.learn.common.CommonResponse;
import com.xq.learn.order.util.JsonUtil;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoqiang
 * @date 2020/3/23 0:11
 */
@Configuration
public class MallErrorDecoder implements ErrorDecoder
{
    private static final Logger logger = LoggerFactory.getLogger(MallErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response)
    {
        try
        {
            String respJson = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            logger.error("invoke response: {}", respJson);
            // 判断是否需要熔断
            CommonResponse resp = JsonUtil.parseObject(respJson, CommonResponse.class);
            if (null != resp && (null == resp.getFallback() || !resp.getFallback()))
            {
                // 该类异常不会进入熔断器
                throw new HystrixBadRequestException(resp.getErrorMessage());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
