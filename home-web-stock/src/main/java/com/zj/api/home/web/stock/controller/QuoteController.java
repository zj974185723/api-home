package com.zj.api.home.web.stock.controller;

import com.zj.api.home.web.integration.kernel.MemcachedFacadeClient;
import com.zj.api.home.web.integration.kernel.QuoteFacadeClient;
import com.zj.api.home.web.kernel.result.WebResult;
import com.zj.api.home.web.kernel.result.util.WebResultUtil;
import com.zj.api.model.cache.constant.CacheKeyConstant;
import com.zj.api.model.stock.QuoteInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by ZJ on 2017/1/18.
 */
@RestController
public class QuoteController {

    @Resource(name = "memcachedFacadeClient")
    private MemcachedFacadeClient memcachedFacadeClient;

    @Resource(name = "quoteFacadeClient")
    private QuoteFacadeClient quoteFacadeClient;

    @RequestMapping(value = "/quote/getStockQuote.json", method = RequestMethod.GET)
    public WebResult getStockQuote(@RequestParam(required = true) String stockCodes) {
        try {
            String[] codes = stockCodes.split(",");
            if (codes.length == 1) {
                QuoteInfo quoteInfo = getQuoteInfoMemCache(stockCodes);
                if (quoteInfo == null) {
                    Map<String, QuoteInfo> quoteInfoMap = quoteFacadeClient.queryStockQuote(stockCodes);
                    return WebResultUtil.getSuccessResult(quoteInfoMap.get(stockCodes));
                }
                return WebResultUtil.getSuccessResult(quoteInfo);
            } else {
                List<QuoteInfo> resultList = getQuotes(codes);
                return WebResultUtil.getSuccessResult(resultList);
            }
        } catch (IllegalArgumentException e) {
            return WebResultUtil.getParamsErrorResult(e.getMessage());
        } catch (Exception e) {
            return WebResultUtil.getSystemErrorResult(e.getMessage());
        }
    }

    /**
     * 获取多只股票的行情
     *
     * @param codes
     * @return
     */
    private List<QuoteInfo> getQuotes(String[] codes) {
        List<QuoteInfo> resultList = new ArrayList<QuoteInfo>();
        for (String code : codes) {
            QuoteInfo quoteInfo = getQuoteInfoMemCache(code);
            if (quoteInfo != null) {
                resultList.add(quoteInfo);
            } else {
                Map<String, QuoteInfo> quoteInfoMap = quoteFacadeClient.queryStockQuote(code);
                QuoteInfo tmpQuoteInfo = quoteInfoMap.get(code);
                resultList.add(tmpQuoteInfo);
            }
        }
        return resultList;
    }

    private QuoteInfo getQuoteInfoMemCache(String code) {
        return memcachedFacadeClient.get(CacheKeyConstant.QUOTE_INFO_KEY + code);
    }

}
