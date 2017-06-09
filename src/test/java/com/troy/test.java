package com.troy;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

/**
 * Created by 12546 on 2016/11/11.
 */
public class test {
    public static void main(String[] args){
        String path="O:\\BaiduYunDownload\\spring-boot-reference-guide.pdf";
        InputStream fis = null;
        try {
            fis = new FileInputStream(path);
            String md5 = DigestUtils.md5Hex(fis);
            IOUtils.closeQuietly(fis);
            System.out.println("MD5:"+md5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
