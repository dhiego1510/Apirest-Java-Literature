package com.alura.literature.util;

public class StringUtil {
  public static String limitLength(String string, int maxLength) {
    if (string.length() <= maxLength) {
      return string;
    } else {
      return string.substring(0, maxLength);
    }
  }
}
