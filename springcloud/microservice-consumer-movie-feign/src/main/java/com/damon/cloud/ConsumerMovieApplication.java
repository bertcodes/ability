package com.damon.cloud;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * FileName: ConsumerMovieApplication
 *
 * @author caobo
 * @create 2018-8-12 16:56
 * @since 1.0.0
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerMovieApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ConsumerMovieApplication.class,args);







        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost  = new HttpPost("http://192.168.31.169:9000/");

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("scope","project"));
        parameters.add(new BasicNameValuePair("q","java"));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
        httpPost.setEntity(formEntity);
        httpPost.setHeader(
          "User-Agent",
          "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                  "Chrome/61.0.3163.100 Safari/537.36"
        );

        CloseableHttpResponse response = null;
        response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == 200){
            String content = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(content);
        }

        if (response != null){
            response.close();
        }
        httpClient.close();







    }
}
