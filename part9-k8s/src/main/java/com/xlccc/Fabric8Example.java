package com.xlccc;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

/**
 * @author Linker
 * @date 10/15/2023 11:40 PM
 * @description：
 * Fabric8 客户端库：Fabric8 是一个基于Kubernetes和OpenShift的Java客户端库，可以方便地与 Kubernetes 集群进行通信，如创建、启动和停止容器等。
 */
public class Fabric8Example {
    public static void main(String[] args) {
        // 创建 Kubernetes 客户端
        DefaultKubernetesClient client = new DefaultKubernetesClient();

        // 创建 Pod 对象
        Pod pod = new PodBuilder()
                .withNewMetadata().withName("my-pod").endMetadata()
                .withNewSpec()
                .addNewContainer().withName("my-container")
                .withImage("nginx:latest")
                .addNewPort().withContainerPort(80).endPort()
                .endContainer()
                .endSpec()
                .build();

        // 创建 Pod
        client.pods().create(pod);
    }
}
