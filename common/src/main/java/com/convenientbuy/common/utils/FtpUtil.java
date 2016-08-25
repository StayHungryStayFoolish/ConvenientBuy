package com.convenientbuy.common.utils;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
