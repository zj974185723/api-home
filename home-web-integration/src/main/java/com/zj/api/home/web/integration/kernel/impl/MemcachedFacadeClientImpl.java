package com.zj.api.home.web.integration.kernel.impl;

import com.zj.api.home.web.integration.kernel.MemcachedFacadeClient;
import com.zj.api.kernel.core.facade.MemcachedFacade;
import com.zj.api.kernel.core.facade.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by js on 2017/1/19.
 */
@Service("memcachedFacadeClient")
public class MemcachedFacadeClientImpl implements MemcachedFacadeClient {


    @Resource(name = "memcachedFacade")
    private MemcachedFacade memcachedFacade;


    public <V extends Serializable> V get(String key) {
        Result<V> result = memcachedFacade.get(key);
        if (result == null || !result.isSuccess()) {
            throw new IllegalArgumentException(result == null ? "api-kernel处理失败" : result.getMessage());
        }
        return result.getT();
    }
}
