import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能描述
 *
 * @author: Linker
 * @date: 2023年12月10日 15:09
 */
public class SimpleHttpServer {
    public static void main(String[] args) {
        int port = 8080; // 设置服务器监听端口

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                processRequest(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRequest(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String requestLine = reader.readLine();
        System.out.println("Request Line: " + requestLine);

        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/html\r\n");
        writer.write("\r\n");
        writer.write("<h1>Hello, World!</h1>");
        writer.flush();

        writer.close();
        reader.close();
        socket.close();
    }
}
