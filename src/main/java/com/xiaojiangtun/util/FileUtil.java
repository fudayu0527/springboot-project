package com.xiaojiangtun.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class FileUtil {

    /**
     * 绝对路径
     **/
    public static String absolutePath = "";

    /**
     * 静态目录
     **/
    public static String staticDir = "temp";

    /**
     * 文件存放的目录
     **/
    public static String fileDir = "/error/";


    /**
     * 获取扩展名,带点
     *
     * @param fileName
     * @return
     */
    public static String getExtention(String fileName) {
        if (fileName != null && fileName.length() > 0 && fileName.lastIndexOf(".") > -1) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }

    /**
     * 获取文件名
     *
     * @param fileName
     * @return
     */
    public static String getfileName(String fileName) {
        if (fileName != null && fileName.length() > 0 && fileName.lastIndexOf(".") > -1) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        }
        return "";
    }

    /**
     * 创建文件夹路径
     */
    public static void createDirIfNotExists() {
        if (!absolutePath.isEmpty()) {
            return;
        }
        //获取跟目录
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取根目录失败，无法创建上传目录！");
        }
        if (!file.exists()) {
            file = new File("");
        }
        absolutePath = file.getAbsolutePath();
        File upload = new File(absolutePath, staticDir);
        log.info("创建文件夹路径：" + upload.getPath());
        if (!upload.exists()) {
            upload.mkdirs();
        }
    }

    /**
     * 创建文件
     */
    public static void createNewFile(String fileUrl) {
        createDirIfNotExists();
        File document = new File(absolutePath + "/" + staticDir, fileUrl);
        log.info("临时文件全路径：" + document.getPath());
        try {
            if (!document.exists()) {
                document.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("创建文件失败！");
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File document = new File(absolutePath + "/" + staticDir, sPath);
        // 路径为文件且不为空则进行删除
        if (document.isFile() && document.exists()) {
            document.delete();
            flag = true;
        }
        return flag;
    }
}
