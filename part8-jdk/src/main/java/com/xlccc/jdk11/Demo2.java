//package com.xlccc.jdk11;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
///**
// * @author Linker
// * @date 10/15/2023 8:02 PM
// * @description：
// */
//public class Demo2 {
//    public static void main(String[] args) throws Exception {
//        // 创建 HTTP/2 客户端
//        HttpClient client = HttpClient.newHttpClient();
//
//        // 创建 HTTP 请求
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("https://example.com"))
//                .build();
//
//        // 发送 HTTP 请求，并获取响应
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        // 处理响应
//        System.out.println("Response Code: " + response.statusCode());
//        System.out.println("Response Body: " + response.body());
//    }
//}
