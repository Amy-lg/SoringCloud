package com.power.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回对象类
 * @Accessors(chain = true) 采用链式编程
 * @author Amy
 * @since 2024/07/26
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;

    // 构造方法赋值日期类字符串
    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 请求成功返回
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(ResultCodeEnum.RC200.getCode());
        resultData.setMessage(ResultCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 请求失败
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
