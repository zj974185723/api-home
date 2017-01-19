package com.zj.api.home.web.integration.kernel;

import com.zj.api.model.stock.QuoteInfo;

import java.util.Map;

/**
 * Created by js on 2017/1/19.
 */
public interface QuoteFacadeClient {

    Map<String, QuoteInfo> queryStockQuote(String stockCodes);

}
