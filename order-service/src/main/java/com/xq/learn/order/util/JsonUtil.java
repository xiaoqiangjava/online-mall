package com.xq.learn.order.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.xq.learn.exception.MallException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaoqiang
 * @date 2020/3/23 0:27
 */
public class JsonUtil
{
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static
    {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    /**
     * 将json转换为Object对象
     * @param json json字符串
     * @param cls 需要转换的对象类型
     * @param <T> 转换的类型
     * @return Object对象的类型
     */
    public static <T> T parseObject(String json, Class<T> cls)
    {
        try
        {
            return mapper.readValue(json, cls);
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
            throw new MallException("mall.0000", "Interval error.");
        }
    }

    /**
     * 将对象转换为json对象
     * @param object Java对象
     * @return json
     */
    public static String getJson(Object object)
    {
        try
        {
            return mapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            logger.error(e.getMessage(), e);
        }

        return null;
    }
}
