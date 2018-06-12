package com.taotao.controller;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by hao on 2018/6/12.
 */
public class FTPTest {

    @Test
    public void testFTPClient() throws IOException {
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接
        ftpClient.connect("101.69.255.132",21);
        //登陆ftp服务器，使用用户名和密码
        ftpClient.login("ftpall","123456");
        //上传文件
        //读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\hao\\Desktop\\keke.jpg"));
        //设置上传的路径
        ftpClient.changeWorkingDirectory("/tt");
        //修改上传文件的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //第一个参数：服务端文件名
        //第二个参数：上传文档的inputStream
        ftpClient.storeFile("hello.jpg",inputStream);
        //关闭连接
        ftpClient.logout();
    }

    @Test
    public void testFtpUtil() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\hao\\Desktop\\keke.jpg"));
        FtpUtil.uploadFile("101.69.255.132",21,"ftpall","123456","/tt","/2018/06/12","hello.jpg",inputStream);
    }
}
