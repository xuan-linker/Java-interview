package com.xlccc.simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Linker
 * @date 10/8/2023 11:07 PM
 * @description： RPC-Frame
 * 简单的RPC框架，通过网络通信实现了客户端和服务端之间的方法调用和参数传递。
 * 当服务端收到客户端的请求时，会根据请求的方法名和参数执行相应的方法，并将结果返回给客户端。
 * 在客户端引用远程服务时，通过动态代理对象来处理方法调用，并将调用请求发送给服务端，最终获取到方法执行的结果
 */
public class RpcFramework {
    /**
     * export 方法：该方法用于将服务对象暴露在指定的端口上，以便远程客户端调用。
     * 它接受两个参数：要暴露的服务对象 service 和端口号 port。在该方法中，
     * 我们通过创建一个 ServerSocket 对象来监听指定的端口，并在循环中不断接受客户端的连接请求。
     * 每当有新的连接请求时，我们创建一个新线程（RpcHandler 对象），处理客户端的请求。
     *
     * @param service 要暴露的服务对象
     * @param port    端口号
     */
    public static void export(Object service, int port) {
        //ServerSocket监听指定的端口
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            //循环中不断接受客户端的连接请求
            while (true) {
                Socket socket = serverSocket.accept();
                //有新的连接请求时，我们创建一个新线程（RpcHandler 对象），处理客户端的请求
                //
                new Thread(new RpcHandler(socket, service)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * refer 方法：该方法用于在远程客户端引用远程服务。它接受三个参数：远程服务的接口类 interfaceClass、服务端的主机地址 host 和端口号 port。
     * 在该方法中，我们使用 Proxy.newProxyInstance 方法创建一个动态代理对象，该代理对象实现了指定接口 interfaceClass 的所有方法，
     * 并将方法调用转发给 RpcInvocationHandler 处理器。
     *
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(Class<T> interfaceClass, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                new RpcInvocationHandler(host, port));
    }

    /**
     * RpcHandler 类：这是一个实现了 Runnable 接口的内部类，用于处理客户端的请求。
     * 在 run 方法中，我们首先通过 ObjectInputStream 从客户端的输入流中读取到方法名、参数类型和参数值，
     * 然后利用反射机制执行相应的方法，并将结果通过 ObjectOutputStream 写回客户端的输出流中。
     */
    private static class RpcHandler implements Runnable {
        private Socket socket;
        private Object service;

        public RpcHandler(Socket socket, Object service) {
            this.socket = socket;
            this.service = service;
        }

        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Method method = service.getClass().getMethod(methodName, parameterTypes);
                Object result = method.invoke(service, arguments);
                output.writeObject(result);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 这是一个实现了 InvocationHandler 接口的内部类，用于处理远程方法的调用。在 invoke 方法中，
     * 我们首先创建一个连接到服务端的 Socket 对象，
     * 然后通过 ObjectOutputStream 将方法名、参数类型和参数值写入输出流，
     * 接着使用 ObjectInputStream 从输入流中读取结果并返回给调用方。
     */
    private static class RpcInvocationHandler implements InvocationHandler {
        private String host;
        private int port;

        public RpcInvocationHandler(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                socket = new Socket(host, port);
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);
                return input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}