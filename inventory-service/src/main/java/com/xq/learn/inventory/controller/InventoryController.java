package com.xq.learn.inventory.controller;

import com.xq.learn.common.CommonResponse;
import com.xq.learn.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存相关controller控制器
 * @author xiaoqiang
 * @date 2020/3/22 14:51
 */
@RestController
@RequestMapping("/v1/mall")
public class InventoryController
{
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    /**
     * 扣减库存
     * @param goodId
     * @return
     */
    @RequestMapping(value = "goods/{good_id}/stock/{num}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponse reduceStock(@PathVariable("good_id") String goodId, @PathVariable("num") int num)
    {
        CommonResponse response = new CommonResponse(true, "Succeed to reduce stock.");
        logger.info("good id({}), reduce stock.", goodId);
        // 扣减库存
        inventoryService.reduceStock(goodId, num);
        return response;
    }
}
