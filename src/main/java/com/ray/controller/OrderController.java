package com.ray.controller;

import com.ray.controller.response.CommonReturnType;
import com.ray.controller.viewobject.UserVO;
import com.ray.error.BusinessException;
import com.ray.error.EmBusinessError;
import com.ray.service.OrderService;
import com.ray.service.model.OrderModel;
import com.ray.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller("order")
@RequestMapping("/order")//url通过/order访问
@CrossOrigin(allowCredentials = "true",allowedHeaders = {"*"})
public class OrderController extends  BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;
    /**
     * 下单请求
     * @param itemId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/createorder",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name="itemId")Integer itemId,
                                        @RequestParam(name = "amount")Integer amount,
                                        @RequestParam(name = "promoId",required = false)Integer promoId) throws BusinessException {
        //获取用户的登录信息
        Boolean isLogin=(Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");//(Boolean)防止报空指针错误
        if(isLogin==null||!isLogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户还未登录");
        }
        UserVO userVO = (UserVO) httpServletRequest.getSession().getAttribute("LOGIN_USER") ;
        OrderModel orderModel =orderService.createOrder(userVO.getId(),itemId,promoId,amount);
        return  CommonReturnType.create(null);
    }
}
