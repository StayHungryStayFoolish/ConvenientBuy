package com.convenientbuy.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by bonismo@hotmail.com
 * 下午8:52 on 16/10/13.
 */
public interface PictureService {

    Map uploadPicture(MultipartFile uploadFile);
}
