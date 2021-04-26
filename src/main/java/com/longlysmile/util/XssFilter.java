package com.longlysmile.util;

/**
 * @author Admin
 */
public class XssFilter {

    public static String transform(String value) {
        String s;
        s = value.replace("&", "&amp;");
        s = s.replace("<", "&lt;");
        s = s.replace(">", "&gt;");
        s = s.replace(" ", "&nbsp;");
        s = s.replace("\\'", "&#39;");
        s = s.replace("\\\"", "&quot;");
        return s;
    }

    public static boolean checkXss(String value){
        value = value.toLowerCase().replace(" ","");
        if(value.contains("<script") || value.contains("javascript")){
                return true;
        }

        return false;
    }
}

