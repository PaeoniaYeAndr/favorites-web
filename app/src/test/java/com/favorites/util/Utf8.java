package com.favorites.util;

public class Utf8 {

  public static String enUnicode(String str) { // 将汉字转换为16进制数
    String st = "";
    try {
      // 这里要非常的注意,在将字符串转换成字节数组的时候一定要明确是什么格式的,这里使用的是gb2312格式的,还有utf-8,ISO-8859-1等格式
      byte[] by = str.getBytes("GB2312");
      for (int i = 0; i < by.length; i++) {
        String strs = Integer.toHexString(by[i]);
        System.out.println("str = " + strs);
        if (strs.length() > 2) {
          strs = strs.substring(strs.length() - 2);
          System.out.println("substring  str= " + strs);
        }
        st += strs;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return st;
  }

  public static void main(String[] args) {
    String s = "华";
    String s1 = enUnicode(s);
    System.out.println(s1);
  }
}