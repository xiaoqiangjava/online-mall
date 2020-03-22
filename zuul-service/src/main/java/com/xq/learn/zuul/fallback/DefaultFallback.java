package com.xq.learn.zuul.fallback;

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
 * 默认的zuul fallback
 * @author xiaoqiang
 * @date 2020/3/22 19:08
 */
@Component
public class DefaultFallback implements FallbackProvider
{
    @Override
    public String getRoute()
    {
        return RouteConstant.DEFAULT;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause)
    {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException
            {
                return new ByteArrayInputStream("fallback".getBytes(StandardCharsets.UTF_8));
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
