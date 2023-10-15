package com.xlccc;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Linker
 * @date 10/15/2023 11:40 PM
 * @description：
 * Kubernetes 提供了一组 RESTful API，可以通过 HTTP 请求方式与其进行交互。Java 应用可以通过 Java 的 HTTP 客户端库，如 java.net.HttpURLConnection 或 Apache HttpClient 等，向 Kubernetes 发送 API 请求，以实现对容器的部署和管理等操作
 */
public class ApiExample {
    public static void main(String[] args) throws IOException {
        // 设置 API URL 和请求参数
        String apiURL = "http://localhost:8001/api/v1/namespaces/default/pods";
        String postData = "{\"apiVersion\": \"v1\", \"kind\": \"Pod\", \"metadata\": {\"name\": \"my-pod\"}, \"spec\": {\"containers\": [{\"name\": \"my-container\", \"image\": \"nginx:latest\", \"ports\": [{\"containerPort\": 80}]}]}}";

        // 创建 HTTP 连接
        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        // 发送 API 请求
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(postData.getBytes());
            outputStream.flush();
        }

        // 处理 API 响应
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        InputStream inputStream = responseCode < 400 ? connection.getInputStream() : connection.getErrorStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }
            System.out.println("Response Body:\n" + response.toString());
        }
    }
}
