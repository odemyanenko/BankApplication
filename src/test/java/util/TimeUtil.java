package util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeUtil {
  public static Timestamp getCurrentDateTime(){
    return new Timestamp(System.currentTimeMillis());
  }

  public static String getUTCFormatTimestamp(Timestamp date){
    DateFormat dfActual = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    dfActual.setTimeZone(TimeZone.getTimeZone("UTC"));
    return dfActual.format(date);
  }
}
