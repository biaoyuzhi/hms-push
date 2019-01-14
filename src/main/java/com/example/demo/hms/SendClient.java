package com.example.demo.hms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * Created by wuzh on 2019/1/11.
 * 对外暴露的华为推送实现类
 */
public class SendClient {
    private static final String APP_ID = "100584647";//根据自己华为开发者联盟中应用服务->PUSH->产品名称(XXX)->服务信息->APP ID
    private static final String APP_SECRET = "bd14e481aa6d9ec963080186df5c9444";//根据自己华为开发者联盟中应用服务->PUSH->产品名称(XXX)->服务信息->APP SECRET
    private static final String ACCESS_TOKEN_URL = "https://login.cloud.huawei.com/oauth2/v2/token";
    private static final String PUSH_URL = "https://api.push.hicloud.com/pushsend.do?nsp_ctx=";
    private static volatile long TOKEN_EXPIRED_TIME;  //accessToken的过期时间
    private static volatile String ACCESS_TOKEN;    //已经获得的accessToken

    //推送主方法
    public String push(Payload payload, JSONArray deviceTokens) {

        String pushResult = null;
        try {
            getAccessToken();

            //推送消息到华为PUSH平台
            String postBody = MessageFormat.format(
                    "access_token={0}&nsp_svc={1}&nsp_ts={2}&device_token_list={3}&payload={4}",
                    URLEncoder.encode(ACCESS_TOKEN, "UTF-8"),
                    URLEncoder.encode("openpush.message.api.send", "UTF-8"),
                    URLEncoder.encode(String.valueOf(System.currentTimeMillis() / 1000), "UTF-8"),
                    URLEncoder.encode(deviceTokens.toString(), "UTF-8"),
                    URLEncoder.encode(JSONObject.toJSONString(payload), "UTF-8"));
            String postUrl = PUSH_URL + URLEncoder.encode("{\"ver\":\"1\", \"appId\":\"" + APP_ID + "\"}", "UTF-8");

            pushResult = HmsUtils.httpPost(postUrl, postBody, 5000, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pushResult;

    }

    //获取access_token
    private String getAccessToken() throws Exception {

        //判断access_token是否存在，是否过期
        if (TOKEN_EXPIRED_TIME <= System.currentTimeMillis() || ACCESS_TOKEN == null){
            String msgBody = MessageFormat.format("grant_type=client_credentials&client_secret={0}&client_id={1}",
                    URLEncoder.encode(APP_SECRET, "UTF-8"), APP_ID);

            String response = HmsUtils.httpPost(ACCESS_TOKEN_URL, msgBody, 5000, 5000);
            JSONObject obj = JSONObject.parseObject(response);
            TOKEN_EXPIRED_TIME = System.currentTimeMillis() + (obj.getLong("expires_in") - 5 * 60) * 1000;
            ACCESS_TOKEN = obj.getString("access_token");
            System.err.println(LocalDateTime.now() +"获得的access_token是:"+ACCESS_TOKEN);
        }
        return ACCESS_TOKEN;

    }


}
