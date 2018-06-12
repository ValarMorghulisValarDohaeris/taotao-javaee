package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传图片controller
 * Created by hao on 2018/6/12.
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map map = pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把结果Map转换成json格式的字符串
        String s = JsonUtils.objectToJson(map);
        return s;
    }

}
