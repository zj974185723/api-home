package com.zj.api.home.web.integration.kernel.impl;

import com.zj.api.home.web.integration.kernel.QuoteFacadeClient;
import com.zj.api.kernel.core.facade.QuoteFacade;
import com.zj.api.kernel.core.facade.result.Result;
import com.zj.api.model.stock.QuoteInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by js on 2017/1/19.
 */
@Service("quoteFacadeClient")
public class QuoteFacadeClientImpl implements QuoteFacadeClient {

    @Resource(name = "quoteFacade")
    private QuoteFacade quoteFacade;


    public Map<String, QuoteInfo> queryStockQuote(String stockCodes) {
        Result<Map<String, QuoteInfo>> result = quoteFacade.queryStockQuote(stockCodes);
        if (result == null || !result.isSuccess()) {
            throw new IllegalArgumentException(result == null ? "api-kernel处理失败" : result.getMessage());
        }
        return result.getT();
    }
}
