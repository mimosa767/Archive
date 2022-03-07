package com.quantum;

import com.quantum.utils.DeviceUtils;

import java.util.Calendar;

public class Helper
{
  public static String getCurrentDay()
  {
    return getCurrentDay(0);
  }

  public static String getCurrentDay(int plus)
  {
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    dayOfMonth = dayOfMonth + plus;

    return String.valueOf(dayOfMonth);
  }

  public static void miniScrollDown()
  {
    DeviceUtils.swipe("50%,50%", "50%,45%");
  }

  public static void ScrollDown()
  {
    DeviceUtils.swipe("50%,60%", "50%,10%");
  }
}
