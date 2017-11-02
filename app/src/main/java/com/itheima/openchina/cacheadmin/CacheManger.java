package com.itheima.openchina.cacheadmin;

import android.os.Environment;

import com.itheima.openchina.appcontrol.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * user:doctorhe
 * 北京传智播客android黑马程序员104期
 */
public class CacheManger {

    // sd/abc
    // 在这里如果是多级目录是需要手动去创建,如果单级不需要创建
    // sd/包名/cache/
    private String saveCacheDir = "";

    private CacheManger() {
        saveCacheDir = Environment.getExternalStorageDirectory().getPath() + File.separator
                + App.context.getPackageName() + File.separator + "cache";

        File saveDir = new File(saveCacheDir);

        if (!saveDir.exists()) {
            saveDir.mkdirs();// 多级目录创建
        }
    }

    private static CacheManger sCacheManger = new CacheManger();

    public static CacheManger getInstance() {
        return sCacheManger;
    }

    // 保存数据
    public void saveData(String jsonContent, String url) {

        // 用md5加密
        try {
            String fileName = md5URl(url);
            File file = new File(saveCacheDir, fileName);
            // 因为文件是需要保存唯一的,而且不能有特殊字符,把url用md5加密

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(jsonContent.getBytes());

            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String md5URl(String url) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            // 初始化md5类
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(url.getBytes());

            byte[] bytes = messageDigest.digest();
            for (int i = 0; i < bytes.length; i++) {

                // System.out.println("当前的byte[]" + Integer.toHexString(bytes[i]&0xff));
                stringBuffer.append(Integer.toHexString(bytes[i] & 0xff));
            }

            return stringBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 取数据

    public String getCacheData(String url) {

        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream = null;
        try {
            String fileName = md5URl(url);
            File cacheFile = new File(saveCacheDir, fileName);

            fileInputStream = new FileInputStream(cacheFile);

            int len = -1;
            byte[] buffer = new byte[1024];

            while ((len = fileInputStream.read(buffer)) != -1) {
                String readContent = new String(buffer, 0, len);
                stringBuffer.append(readContent);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return stringBuffer.toString();


    }
}
