package com.leyi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/3/16.
 */

public class HTMLSpirit {
    /**

     * 去掉所有的HTML标签

     *

     * @param htmlStr

     * @return

     */
    public static String delHTMLTag(String htmlStr) {

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式

        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_span = "<span[^>]*?>[\\s\\S]*?<\\/span>"; // 定义style的正则表达式
        String regEx_class = "<p class=\\\"MsoNormal\\\">\\r\\n\\t[^>]*?>[\\s\\S]*?<\\/  class=\\\"MsoNormal\\\">\\r\\n\\t /p>"; // 定义style的正则表达式

        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式


        Pattern p_script = Pattern.compile(regEx_script,
                Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签


        Pattern p_style = Pattern
                .compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_span = Pattern
                .compile(regEx_span, Pattern.CASE_INSENSITIVE);
        Matcher m_span = p_span.matcher(htmlStr);
        htmlStr = m_span.replaceAll(""); // 过滤span标签


        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签


        Pattern p_class = Pattern.compile(regEx_class, Pattern.CASE_INSENSITIVE);
        Matcher m_class = p_class.matcher(htmlStr);
        htmlStr = m_class.replaceAll(""); // 过滤标签

        return htmlStr.trim().replaceAll(" ", "").replace("/", "").replace("\"","").replace("\r","").replace("\n","").replace("\t","");   // 返回文本字符串
//        return htmlStr.trim().replaceAll(" ", ""); // 返回文本字符串

    }

    /**

     * 从HTML获取图片url

     * @param htmlStr

     * @return

     */
    public static List getImgStr(String htmlStr) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        List pics = new ArrayList();
        String regEx_img = "]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
