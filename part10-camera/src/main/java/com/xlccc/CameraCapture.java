package com.xlccc;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Linker
 * @date 10/15/2023 11:58 PM
 * @description：
 * 确保你已经正确安装了 OpenCV 并设置了正确的环境变量。你可以在系统的环境变量中添加一个名为 PATH_TO_OPENCV\build\java\x64（对于 64 位系统）或 PATH_TO_OPENCV\build\java\x86（对于 32 位系统）的条目，其中 PATH_TO_OPENCV 是你的 OpenCV 安装路径。
 *
 * 如果你手动下载了 OpenCV 的本地库文件，请确保将其复制到适当的路径下。对于 Windows 用户，可以将 opencv_java460.dll 文件复制到 C:\Windows\System32 目录下或你的项目根目录下。对于 macOS 用户，可以将 libopencv_java460.dylib 文件复制到 /usr/local/lib 目录下。对于 Linux 用户，可以将 libopencv_java460.so 文件复制到 /usr/local/lib 目录下。
 *
 * 在代码中使用 System.loadLibrary("opencv_java460") 显式加载本地库文件。确保在调用 OpenCV 相关的功能之前执行此操作。
 */

public class CameraCapture {
    public static void main(String[] args) {
        // 加载 OpenCV 库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 创建 VideoCapture 对象，打开默认摄像头
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("无法打开摄像头！");
            return;
        }

        // 设置摄像头参数（可选）
        camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);

        // 创建 Mat 对象来存储图像帧
        Mat frame = new Mat();

        // 读取摄像头图像帧并显示
        while (true) {
            if (camera.read(frame)) {
                // 在这里添加你对图像帧的处理逻辑

                // 显示图像帧
                imshow("Camera", frame);
            }
        }
    }

    // 辅助方法：显示图像窗口
    private static void imshow(String windowName, Mat image) {
        // 根据 Mat 对象创建 BufferedImage
        BufferedImage bufImage = null;
        byte[] data = new byte[image.rows() * image.cols() * (int) image.elemSize()];
        image.get(0, 0, data);
        if (image.channels() == 3) {
            bufImage = new BufferedImage(image.cols(), image.rows(), BufferedImage.TYPE_3BYTE_BGR);
        } else if (image.channels() == 1) {
            bufImage = new BufferedImage(image.cols(), image.rows(), BufferedImage.TYPE_BYTE_GRAY);
        }
        bufImage.getRaster().setDataElements(0, 0, image.cols(), image.rows(), data);

        // 创建并显示图像窗口
        JFrame frame = new JFrame(windowName);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
