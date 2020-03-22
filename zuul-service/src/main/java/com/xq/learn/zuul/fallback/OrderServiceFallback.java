package com.xq.learn.zuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.xq.learn.zuul.constant.RouteConstant;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 自定义zuul中hystrix的fallback，需要实现FallbackProvider接口
 * 通过该接口只能配置服务级别的fallback。
 * @author xiaoqiang
 * @date 2020/3/22 18:54
 */
@Component
public class OrderServiceFallback implements FallbackProvider
{
    @Override
    public String getRoute()
    {
        // 返回需要配置fallback的服务路由, 跟配置中zuul.routes中配置的服务名称相对应
        return RouteConstant.ORDER_SERVICE;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause)
    {
        // 配置当前路由fallback返回信息
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException
            {
                String message = "{\n" +
                        "\t\"is_success\": true,\n" +
                        "\t\"message\": \"Succeed to request by fallback. \"\n" +
                        "}";
                return new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
