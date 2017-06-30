/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemfileanalyse;

import java.io.FileInputStream;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 *
 * @author mark
 */
public class FileIdCreate implements FileIdInterface{
    
    /*
    * Default use md5
    */
    public String createFileId(String filepath) {
        return getFileMd5(filepath);
    }
    
    /*
    * method to create file id
    */
    public String createFileId(String filepath, int Method) {
        if (FileIdInterface.Method_MD5 == Method) {
            return getFileMd5(filepath);
        }
        return null;
    }
    
    private String getFileMd5(String path) {
        FileInputStream fis = null;
        String md5Value = null;
        //System.out.println("Path:" + path);
        try {
            //并非完整计算MD5值,以减少计算时间
            //0~64M完整提取文件，64 ~ 64 *64,每64m提取1m,更大的文件以后每64m提取1k
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(path);
            byte[] buffer = new byte[8192];
            int length = -1;
            int cnt  = 0;
            while ((length = fis.read(buffer)) != -1) {
                if (cnt >= 8192) {
                    if (cnt >= 67108864) {  // 8192 *8192
                        int size = cnt - 67108864;
                        if (size % 8192 != 0) {
                            continue;
                        }
                    } else {
                        int size = cnt - 8192;
                        if (size % 256 != 0) {
                            continue;
                        }
                    }
                }
                md.update(buffer, 0, length);
                cnt ++;
            }
            BASE64Encoder base64en = new BASE64Encoder();
            //System.out.println("*:" + md.digest());
            md5Value = base64en.encode(md.digest());
        } catch(Exception ex) {
             System.out.println("Error:" + ex.toString());
        } finally {
           try {
                fis.close();
           } catch(Exception ex) {
               
           }
        }
        return md5Value;
    }
}
