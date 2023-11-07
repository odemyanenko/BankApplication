package util;

import java.sql.Timestamp;

public class TimeUtil {
  public static Timestamp getCurrentDateTime(){
    return new Timestamp(System.currentTimeMillis());
  }
}
