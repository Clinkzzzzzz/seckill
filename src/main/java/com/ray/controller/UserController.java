package com.ray.controller;

import com.alibaba.druid.util.StringUtils;
import com.ray.controller.response.CommonReturnType;
import com.ray.controller.viewobject.UserVO;
import com.ray.error.BusinessException;
import com.ray.error.EmBusinessError;
import com.ray.service.UserService;
import com.ray.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")//spring扫描
@RequestMapping("/user")//url通过/user访问
@CrossOrigin(allowCredentials = "true",allowedHeaders = {"*"})
//允许跨域传输所有的header参数，将用于token放入header域做session共享的跨域请求
public class UserController  extends  BaseController{
    @Autowired
    private  UserService userService;

    @Autowired
    private  HttpServletRequest httpServletRequest;//bean注入是单例模式 但 HttpServletRequest经过spring包装是线程安全的

    /**
     * 登录
     * @param telphone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="telphone")String telphone,
                                  @RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //判空
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone)|| org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //登录服务
        UserModel userModel=userService.validateLoigin(telphone,this.EncodeByMd5(password));

        //登录成功则将登录凭证加入到用户登录成功的session内
        UserVO userVO=convertFromModel(userModel);
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userVO);
        return CommonReturnType.create(null);
    }


    /**
     * 注册
     * @param telphone
     * @param otpCode
     * @param name
     * @param gender
     * @param age
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telphone")String telphone,
                                     @RequestParam(name="otpCode")String otpCode,
                                     @RequestParam(name="name")String name,
                                     @RequestParam(name="gender")Integer gender,
                                     @RequestParam(name="age")Integer age,
                                     @RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证用户手机号和对应的otpcode
        String inSessionOtpCode=(String) this.httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(otpCode,inSessionOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    /**
     * 用户获取otp短信接口
     * @param telphone
     * @return
     */
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone){
        //需要按照一定的规则生成otp验证码
        Random random=new Random();
        int randomInt= random.nextInt(99999);
        randomInt+=10000;
        String optCode=String.valueOf(randomInt);

        //将otp验证码同对应用户的的手机关联,(企业级应用中应用都是分布式的 常用redis，将tel和otp做到redis中)先使用httpsession的方式绑定他的手机和OTPCODE
        httpServletRequest.getSession().setAttribute(telphone,optCode);

        //将otp验证码通过短信通道发送给用户，省略（需要购买第三方短信服务通道，以http-post方式将短信模板post给手机）
        System.out.println("telphone="+telphone+"&optCode="+optCode);

        return CommonReturnType.create(null);
    }

    /**
     * 调用service服务获取对应id的用户对象并返回给前端
     * @param id
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);

        //若获取用户信息不存在
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);//被tomcat容器捕获 在BaseControl定义exceptionhandler解决未被controller层吸收的exception
        }

        UserVO userVO= convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO= new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String newStr=base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }
}
