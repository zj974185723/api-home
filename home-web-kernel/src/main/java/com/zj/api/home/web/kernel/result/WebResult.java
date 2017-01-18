package com.zj.api.home.web.kernel.result;

import java.io.Serializable;

/**
 * Created by ZJ on 2017/1/18.
 */
public class WebResult implements Serializable {

    private static final long serialVersionUID = 904453614870441348L;

    private int code;

    private String message;

    private boolean success;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebResult webResult = (WebResult) o;

        if (code != webResult.code) return false;
        if (success != webResult.success) return false;
        if (data != null ? !data.equals(webResult.data) : webResult.data != null) return false;
        return message != null ? message.equals(webResult.message) : webResult.message == null;

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (success ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
