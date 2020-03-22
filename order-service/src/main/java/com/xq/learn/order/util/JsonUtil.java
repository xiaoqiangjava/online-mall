package com.xq.learn.order.util;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @SuppressWarnings("unchecked")
    public static <T> T parseObject(String json, Class<T> cls)
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
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
}
