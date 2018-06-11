package com.leyijf.utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils {
    private static final String ALGORITHM = "RSA";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 手机号判断正则法式
    public static boolean isPhone(String number) {
        String pattern = "^1[3-8]{1}\\d{9}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank((CharSequence) str);
    }


    public static boolean isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object.toString().trim().trim().equals(""))
            return true;
        return false;
    }

    /**
     * 检查String是否为Email类型
     *
     * @param mail
     * @return
     * @author wb_suoyao.shen 2012.04.11
     */
    public static boolean checkEmail(String mail) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.find();
    }

    public static String utf8(String oldStr) {
        String newStr = oldStr;
        try {
            if (oldStr == null || "".equals(oldStr))
                return oldStr;
            newStr = new String(oldStr.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newStr;
    }

    public static boolean isNull(String str) {
        return str == null ? true : false;
    }

    //使用String的split 方法
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    //判断是否包含字母和百分号
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含百分号
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        if (m.matches()) {
            isLetter = true;
        }
        if (str.equals("%")) {
            isDigit = true;
        }
        boolean isRight = isDigit || isLetter;
        return isRight;
    }

    // 过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
