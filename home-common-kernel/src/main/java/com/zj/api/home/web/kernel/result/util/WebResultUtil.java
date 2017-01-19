package com.zj.api.home.web.kernel.result.util;

import com.zj.api.home.web.kernel.result.WebResult;
import com.zj.api.home.web.kernel.result.constant.WebResultCodeConstant;

/**
 * Created by ZJ on 2017/1/18.
 */
public class WebResultUtil {

    /**
     * 获取一个成功的实例
     *
     * @param obj
     * @return
     */
    public static WebResult getSuccessResult(Object obj) {
        WebResult result = new WebResult();
        result.setSuccess(true);
        result.setMessage("");
        result.setCode(WebResultCodeConstant.SUCCESS);
        result.setData(obj);
        return result;
    }

    /**
     * 获得一个系统错误的实例
     *
     * @param message
     * @return
     */
    public static WebResult getSystemErrorResult(String message) {
        WebResult result = new WebResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setCode(WebResultCodeConstant.SYSTEM_ERROR);
        return result;
    }

    /**
     * 获得一个参数错误的实例
     *
     * @param message
     * @return
     */
    public static WebResult getParamsErrorResult(String message) {
        WebResult result = new WebResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setCode(WebResultCodeConstant.PARAMS_ERROR);
        return result;
    }

}
