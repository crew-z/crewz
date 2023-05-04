package environment.project.util;

import java.util.Date;

public class DateUtil {
    // 기능: D-Day
    public static long dateDifference(Date endDate) {
        Date now = new Date();
        return (endDate.getTime() - now.getTime()) / 1000 / 60 / 60 / 24;
    }
}
