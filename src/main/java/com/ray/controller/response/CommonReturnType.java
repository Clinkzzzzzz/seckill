package com.ray.controller.response;

public class CommonReturnType {
    //表明对应请求的返回处理结果success or fail
    //若status=success,则data返回前端需要的json
    //若status=fail则data内使用通用的错误码格式
    private String status;
    private Object data;

    public  static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type= new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return  type;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
