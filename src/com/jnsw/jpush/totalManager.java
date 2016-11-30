package com.jnsw.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.report.ReceivedsResult;

public class totalManager {
	protected static final Logger LOG = LoggerFactory.getLogger(JPush.class);  

	public static JSONObject testtotalManager(String appKey ,String masterSecret,String msg){
		JSONObject json = new JSONObject();
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		 try {
	        ReceivedsResult result = jpushClient.getReportReceiveds(msg);
//	        LOG.debug("Got result - " + result);
//	        LOG.info(result);
	        LOG.info("Got result - " + result);
	        json.put("result", result.received_list);
	    } catch (APIConnectionException e) {
	        // Connection error, should retry later
	        LOG.error("Connection error, should retry later", e);
	
	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	        LOG.error("Should review the error, and fix the request", e);
	        LOG.info("HTTP Status: " + e.getStatus());
	        LOG.info("Error Code: " + e.getErrorCode());
	        LOG.info("Error Message: " + e.getErrorMessage());
	    }
		 return json;
	}
}
