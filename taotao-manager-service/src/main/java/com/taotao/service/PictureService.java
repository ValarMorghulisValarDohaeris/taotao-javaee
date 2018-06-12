package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by hao on 2018/6/12.
 */
public interface PictureService {

    Map uploadPicture(MultipartFile uploadFile);
}
