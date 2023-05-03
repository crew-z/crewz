package environment.project.util;

import environment.project.exception.ResourceNotFoundException;

import java.util.Date;

public class DateUtil {
    /**
     * 메서드: 현재시간에서 endDate 빼는 기능 구현
     *
     * @author 이권석능
     */
    public static long dateDifference(Date endDate) {
        Date now = new Date();

        long diffInMillis = endDate.getTime() - now.getTime();;

        long diffInSeconds = diffInMillis / 1000;
        long diffInMinutes = diffInSeconds / 60;
        long diffInHours = diffInMinutes / 60;
        long diffInDays = diffInHours / 24;

        return diffInDays;
    }
}
