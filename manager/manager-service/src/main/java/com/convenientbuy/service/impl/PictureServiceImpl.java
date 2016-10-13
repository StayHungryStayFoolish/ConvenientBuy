package com.convenientbuy.service.impl;

import com.convenientbuy.common.utils.FtpUtil;
import com.convenientbuy.common.utils.IDUtils;
import com.convenientbuy.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午9:11 on 16/10/13.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    /**
     * 上传图片到远程图片服务器
     * @param uploadFile
     * @return
     */
    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap<>();
        try {
            //生成一个新的文件名
            //取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            String newName = IDUtils.genImageName();
            // 图片名字+后缀格式
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                    FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
            //返回结果
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
            return resultMap;

        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }
}
