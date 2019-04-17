package com.kwdz.blog.api.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一通信对象
 */
@Data
public class ResultModel<T> implements Serializable {
    /**
     * 通信数据
     */
    private T data;
    /**
     * 通信状态 （正常:true 异常: false）
     */
    private boolean flag = true;
    /**
     * 通信描述
     */
    private String msg = "OK"; //描述信息

    /**
     * 内部构造器
     */
    private ResultModel() {

    }

    /**
     * 通过静态方法获取实例
     */
    public static <T> ResultModel<T> of(T data) {
        ResultModel<T> result = new ResultModel<>();
        result.setData(data);
        return result;
    }

    /**
     * 通过静态方法获取实例
     */
    public static <T> ResultModel<T> of(T data, boolean flag, String msg) {
        ResultModel<T> result = new ResultModel<>();
        result.setData(data);
        result.setFlag(flag);
        result.setMsg(msg);
        return result;
    }

}