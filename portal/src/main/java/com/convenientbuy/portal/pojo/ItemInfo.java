package com.convenientbuy.portal.pojo;

import com.convenientbuy.pojo.CbItem;

/**
 * Created by bonismo@hotmail.com
 * 下午9:22 on 16/11/14.
 */
public class ItemInfo extends CbItem {

    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
