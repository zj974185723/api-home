package com.zj.api.home.web.stock.controller;

import com.zj.api.home.web.kernel.result.WebResult;
import com.zj.api.home.web.kernel.result.util.WebResultUtil;
import com.zj.api.kernel.core.facade.MemcachedFacade;
import com.zj.api.kernel.core.facade.QuoteFacade;
import com.zj.api.kernel.core.facade.result.Result;
import com.zj.api.model.stock.QuoteInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ZJ on 2017/1/18.
 */
@RestController
public class QuoteController {

    @Resource(name = "memcachedFacade")
    private MemcachedFacade memcachedFacade;

    @Resource(name = "quoteFacade")
    private QuoteFacade quoteFacade;

    private final String QUOTE_INFO_KEY = "QUOTE_INFO_KEY_";

    @RequestMapping(value = "/quote/getStockQuote.json", method = RequestMethod.GET)
    public WebResult getStockQuote(@RequestParam(required = true) String stockCodes) {
        String[] codes = stockCodes.split(",");
        if (codes.length == 1) {
            Result<QuoteInfo> result = getQuoteInfoMemCache(stockCodes);
            return WebResultUtil.getSuccessResult(result.getT());
        } else {
            Map<String, QuoteInfo> resultMap = getQuotes(codes);
            return WebResultUtil.getSuccessResult(resultMap);
        }
    }

    /**
     * 获取多只股票的行情
     *
     * @param codes
     * @return
     */
    private Map<String, QuoteInfo> getQuotes(String[] codes) {
        Map<String, QuoteInfo> resultMap = new HashMap<String, QuoteInfo>();
        for (String code : codes) {
            Result<QuoteInfo> result = getQuoteInfoMemCache(code);
            QuoteInfo quoteInfo = result.getT();
            if (quoteInfo != null) {
                resultMap.put(quoteInfo.getStockCode(), quoteInfo);
            } else {
                Result<Map<String, QuoteInfo>> quoteInfoMap = quoteFacade.queryStockQuote(code);
                QuoteInfo tmpQuoteInfo = quoteInfoMap.getT().get(code);
                resultMap.put(tmpQuoteInfo.getStockCode(), tmpQuoteInfo);
            }
        }
        return resultMap;
    }

    private Result<QuoteInfo> getQuoteInfoMemCache(String code) {
        return memcachedFacade.get(QUOTE_INFO_KEY + code);
    }

}
