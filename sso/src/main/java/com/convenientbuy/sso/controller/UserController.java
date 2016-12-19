package com.convenientbuy.sso.controller;

import com.convenientbuy.common.pojo.Result;
import com.convenientbuy.common.utils.ExceptionUtil;
import com.convenientbuy.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.bsd.RExecClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bonismo@hotmail.com
 * 下午9:47 on 16/12/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        Result result = null;
        //参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = Result.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = Result.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3) {
            result = Result.build(400, "校验内容类型错误");
        }
        // 校验出错
        if (null != result) {
            if (null != callback) {
                // jsonp 跨域请求
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            } else {
                return result;
            }
        }
        try {
            result = service.checkData(param, type);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.build(500, ExceptionUtil.getStackTrace(e));
        }
        return null;
    }
}
