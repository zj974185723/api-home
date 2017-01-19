package com.zj.api.home.web.integration.kernel;


import java.io.Serializable;

/**
 * Created by js on 2017/1/19.
 */
public interface MemcachedFacadeClient {


    <V extends Serializable> V get(String key);


}
