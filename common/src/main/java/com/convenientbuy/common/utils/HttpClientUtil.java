package com.convenientbuy.common.utils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午10:39 on 16/8/25.
 */
public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultStr = "";
        CloseableHttpResponse response = null;

        try {
            // 创建 URI
            URIBuilder builder = new URIBuilder(url);
            if (null != param) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建 Get 请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpClient.execute(httpGet);

            // 如果 get 请求返回状态是200,证明成功,返回 String 类型
            if (response.getStatusLine().getStatusCode() == 200) {
                resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }


}
