package com.ray.service;

import com.ray.error.BusinessException;
import com.ray.service.model.OrderModel;

public interface OrderService {
    //1.通过前端url上传过来的秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已开始。（可扩展性强，且性能较高）
    /*
    新增订单
     */
    OrderModel createOrder(Integer userId , Integer itemId,Integer promoId,Integer amount) throws BusinessException;


}
