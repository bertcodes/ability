package com.ability.seckill.result;

/**
 * Copyright (C): :
 * FileName: MsgCode
 *
 * @author caobo
 * @create 2019-1-1 20:42
 * @since 1.0.0
 * Description:
 */
public class CodeMsg {

    private int code;
    private String msg;

    //通用异常
     public static CodeMsg SUCCESS = new CodeMsg(0,"success");
     public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
     public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常：%s");
    //登录模块 5002XX
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500200,"手机号码不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500201,"密码错误");

    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX




    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg findArgs(Object... args){
        int code = this.code;
        String message = String.format(this.msg,args);
        return  new CodeMsg(code,message);
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
