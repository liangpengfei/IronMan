package com.fei.peng.liang.ironman.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 14-8-18.
    使用MD5加密算法，实际上就是把字符串转化成了密文
 */
public class Md5Encoder {
    public static String encode(String password){
        try {
            //获取数字消息的摘要器
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result=digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<result.length;i++){
                int number=result[i]&0xff;
                String str =Integer.toHexString(number);
                if (str.length() == 1){
                    sb.append("0");
                    sb.append(str);
                }else {
                    sb.append(str);
                }
            }
            return  sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
