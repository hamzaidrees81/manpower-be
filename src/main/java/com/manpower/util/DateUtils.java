package com.manpower.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

  private static String dateFormat = "yyyy-MM-dd";

  public static String convertDateToString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(date);
  }
}
