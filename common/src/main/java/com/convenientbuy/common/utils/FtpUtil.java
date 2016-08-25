package com.convenientbuy.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bonismo@hotmail.com
 * 下午9:56 on 16/8/25.
 * <p>
 * Ftp 工具
 */
public class FtpUtil {

    /**
     * FTP 上传文件到服务器
     * @param host url
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @param basePath 基本url
     * @param filePath 文件路径
     * @param fileName 文件名字
     * @param inputStream 输入流
     * @return true 上传成功 / false 上传失败
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String fileName, InputStream inputStream) {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();

        try {
            int reply;
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            reply = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return result;
            }

            if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    if (!ftpClient.changeWorkingDirectory(tempPath)) {
                        if (!ftpClient.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            // 设置文件上传类型为二进制类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftpClient.storeFile(fileName, inputStream)) {
                return result;
            }

            inputStream.close();
            ftpClient.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }



}
