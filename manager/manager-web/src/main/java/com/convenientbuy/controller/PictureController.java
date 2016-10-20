package com.convenientbuy.controller;

import com.convenientbuy.common.utils.JsonUtils;
import com.convenientbuy.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午10:39 on 16/10/20.
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService service;

    /**
     * 上传图片到 FTP 远程服务器
     * file-upload.jsp
     *
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile) {
        Map result = service.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把Result转换成json格式的字符串。
        String json = JsonUtils.objectToJSON(result);
        return json;
    }
}
