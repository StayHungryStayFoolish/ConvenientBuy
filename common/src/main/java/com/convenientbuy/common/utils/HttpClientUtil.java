package com.convenientbuy.common.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午10:39 on 16/8/25.
 *
 * HttpClient 工具,处理 Post / Get / 携带 Json 数据的 Post 请求
 */
public class HttpClientUtil {

    /**
     * Get 请求, 无参
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * Get 请求,有参
     *
     * @param url
     * @param param
     * @return
     */
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


    /**
     * 处理 Post 请求,无参
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 处理 Post 请求,带参
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = "";
        try {
            // 创建 Post 请求
            HttpPost httpPost = new HttpPost();
            if (null != param) {
                List<NameValuePair> pairList = new ArrayList<>();
                for (String key : param.keySet()) {
                    pairList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟 Post 表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairList);
                httpPost.setEntity(entity);
            }

            // 执行 Post 请求
            response = httpClient.execute(httpPost);
            resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }

    /**
     * 处理 json 数据格式的 Post 请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = "";
        try {
            HttpPost httpPost = new HttpPost();
            // 创建一个 json 数据格式的实体对象
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行 Post 请求
            response = httpClient.execute(httpPost);
            resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }
}
