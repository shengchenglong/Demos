package com.scl.cloud.fallback.provider;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
// old version
//import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author shengchenglong
 * @version 1.0
 * @since 17/9/14 11:09 JDK 1.8
 */
@Component
// old version
//public class UserFallbackProvider implements ZuulFallbackProvider {
public class UserFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        // 表明为哪个微服务提供回退
        return "microservice-consumer-movie";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // fallback状态码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("用户微服务暂不可用，请稍后再试".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }

//    old version
//    @Override
//    public ClientHttpResponse fallbackResponse() {
//
//    }
}
