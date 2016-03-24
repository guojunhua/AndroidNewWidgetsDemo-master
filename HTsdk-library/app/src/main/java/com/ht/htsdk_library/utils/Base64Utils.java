package com.ht.htsdk_library.utils;

import android.text.TextUtils;
import android.util.Base64;

import com.ht.htsdk_library.MyApp;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 郭君华 on 2016/1/5.
 * Email：guojunhua3369@163.com
 */
public class Base64Utils {

    //base64解码
    public static byte[] base64Dec(String str) {
        return Base64.decode(str, Base64.DEFAULT);
    }

    /**
     * 转换16进制
     *
     * @param bytes
     * @return
     */
    public static String bytes2hex(byte[] bytes) {
        final String HEX = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    public static boolean isEmpty(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            return true;
        return false;
    }

    public static boolean isEmail(String email) {
        //^(\w+((-\w+)|(\.\w+))*)\+\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$
        //^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$
        //^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    public static boolean isString(String name) {
        String check = "^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }

    public static boolean isContains(String username) {
        String s = "@";
        return username.contains(s);
    }

    public static List<String> dataLength(String userInfo) {
        int needLength = 117;
        List<String> resultList = new ArrayList<String>();
        String subStr;
        int endIndex = 0;
        while (userInfo.length() > 0) {
            // 判断截取的长度
            endIndex = userInfo.length() > needLength ? needLength : userInfo.length();
            // 获得街区后的长度
            subStr = userInfo.substring(0, endIndex);
            // 截取剩余的字符串
            userInfo = userInfo.substring(endIndex);
            resultList.add(subStr);
        }
        return resultList;
    }

    public static String encryptData(String userInfo) {
        StringBuilder sb = null;
        try {
            ArrayList<String> strings = ((ArrayList<String>) dataLength(userInfo));
            ArrayList<byte[]> bytes = new ArrayList<byte[]>();
            PublicKey publicKey = RSAUtils.loadPublicKey(MyApp.publicKeyStr);
            for (int i = 0; i < strings.size(); i++) {
                bytes.add(RSAUtils.encryptData(strings.get(i).getBytes(), publicKey));
            }
            for (int i = 0; i < bytes.size(); i++) {
                sb = new StringBuilder();
                sb.append(bytes2hex(bytes.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
