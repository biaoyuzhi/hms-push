package com.example.demo.hms;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by wuzh on 2019/1/10.
 * HMS操作中获得access_token、消息推送到华为PUSH平台等操作的工具类
 */
public class HmsUtils {

    //post方式提交请求到httpUrl后，获得响应信息
    public static String httpPost(String httpUrl, String data, int connectTimeout, int readTimeout) throws Exception{
        OutputStream outPut = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        try
        {
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.setConnectTimeout(connectTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.connect();

            // POST data
            outPut = urlConnection.getOutputStream();
            outPut.write(data.getBytes("UTF-8"));
            outPut.flush();

            // read response
            int responseCode = urlConnection.getResponseCode();
            if (responseCode < 400)
            {
                in = urlConnection.getInputStream();
            }
            else
            {
                in = urlConnection.getErrorStream();
            }

            List<String> lines = IOUtils.readLines(in, urlConnection.getContentEncoding());
            StringBuffer strBuf = new StringBuffer();
            for (String line : lines)
            {
                strBuf.append(line);
            }
            System.out.println("strBuf.toString:"+strBuf.toString());
            return strBuf.toString();
        }
        finally
        {
            IOUtils.closeQuietly(outPut);
            IOUtils.closeQuietly(in);
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
    }

}
