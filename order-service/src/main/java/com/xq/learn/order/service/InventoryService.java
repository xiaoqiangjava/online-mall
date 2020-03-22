package com.xq.learn.order.service;

import com.xq.learn.common.CommonResponse;
import com.xq.learn.order.service.impl.InventoryServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 使用feign调用库存服务相关接口
 * 需要注意的是该接口的类上面不能加@RequestMapping注解，否则该接口注册bean时会冲突
 * fallback只是调用，没有异常信息，使用fallbackFactory可以在 InventoryFallbackFactory#create中获取异常信息，
 * 但是该异常是被封装过的异常。
 * @author xiaoqiang
 * @date 2020/3/22 15:18
 */
@FeignClient(value = "inventory-service", fallback = InventoryServiceFallback.class)
public interface InventoryService
{
    @RequestMapping(value = "/v1/mall/goods/{good_id}/stock/{num}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CommonResponse reduceStock(@PathVariable("good_id") String goodId, @PathVariable("num") int num);
}
