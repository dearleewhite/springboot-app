package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-15 15:30
 **/

@Data
@ToString
@NoArgsConstructor
public class CommonResultVo<T> implements Serializable {
    private static final long serialVersionUID = 7045142366784960794L;

    /**
     * 状态码：0-成功，1-失败
     * */
    private Integer code;
    /**
     * 错误消息，如果成功可为空或SUCCESS
     * */
    private String msg;
    /**
     * 返回结果数据
     * */
    private T data;

    /**
     * 构造方法
     * @param code
     * @param msg
     */
    public CommonResultVo(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }


    public static final Integer SUCCESS_CODE = 200;
    public static final Integer ERROR_CODE = 500;
    public static final String SUCCESS_MSG = "操作成功";
    public static final String ERROR_MSG = "操作失败";

    /**
     * 默认一个成功的返回对象
     */
    private static final CommonResultVo<?> SUCCESS_CACHE = new CommonResultVo(SUCCESS_CODE, SUCCESS_MSG);

    public static CommonResultVo success(){
        return SUCCESS_CACHE;
    }
    //成功返回数据
    public static <T> CommonResultVo<T> success(T data){
        CommonResultVo commonResultVo = new CommonResultVo();
        commonResultVo.setCode(SUCCESS_CODE);
        commonResultVo.setMsg(SUCCESS_MSG);
        commonResultVo.setData(data);
        return commonResultVo ;
    }

    /**
     * 默认一个失败的返回对象
     */
    private static final CommonResultVo<?> ERROR_CACHE = new CommonResultVo(ERROR_CODE, ERROR_MSG);

    public static CommonResultVo error(){
        return ERROR_CACHE;
    }

    public static CommonResultVo error(String message){
        CommonResultVo commonResultVo = new CommonResultVo();
        commonResultVo.setCode(ERROR_CODE);
        commonResultVo.setMsg(message);
        return commonResultVo;
    }


}
