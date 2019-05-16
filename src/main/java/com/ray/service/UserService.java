package com.ray.service;

import com.ray.error.BusinessException;
import com.ray.service.model.UserModel;

public interface UserService {
    /**
     * 通过用户id获取用户对象
     * @param id
     */
    UserModel getUserById(Integer id);

    void register(UserModel userModel)throws BusinessException;

    /**
     * @param telphone 注册手机号
     * @param encrptPassword 加密后密码
     * @throws BusinessException
     */
    UserModel validateLoigin(String telphone,String encrptPassword) throws BusinessException;

}
