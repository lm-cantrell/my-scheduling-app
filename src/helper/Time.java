package helper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/** Time class provides methods for converting and calculated time related objects.
 * @author Lisa Cantrell
 * */

public abstract class Time {

    /** utcToSysLocal converts object from utc to users system time zone.
     * @param utcZdtObject  */
    public static ZonedDateTime utcToSysLocal (ZonedDateTime utcZdtObject){
        ZoneId sysLocalZone = ZoneId.systemDefault();
        ZonedDateTime sysLocalZdtObject = ZonedDateTime.ofInstant(utcZdtObject.toInstant(), sysLocalZone);

        return sysLocalZdtObject;
    }

    /** localToUtc converts object from users system time zone to utc.
     * @param localZdtObject  */
    public static ZonedDateTime localToUtc(ZonedDateTime localZdtObject){
        ZoneId utcZone = ZoneId.of("UTC");
        ZonedDateTime utcZdtObject = ZonedDateTime.ofInstant(localZdtObject.toInstant(), utcZone);

        return utcZdtObject;
    }

    /** utcToEaster converts object from utc to eastern region time zone.
     * @param utcZdtObject  */
    public static ZonedDateTime utcToEastern (ZonedDateTime utcZdtObject) {
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime easternZdtObject = ZonedDateTime.ofInstant(utcZdtObject.toInstant(), newYorkZone);

        return easternZdtObject;
    }

    /** easternToLocalSys converts object from eastern to users system time zone.
     * @param eastZdtObj  */
    public static ZonedDateTime easternToLocalSys (ZonedDateTime eastZdtObj) {
        ZoneId sysLocalZone = ZoneId.systemDefault();
        ZonedDateTime sysLocalZdtObject = ZonedDateTime.ofInstant(eastZdtObj.toInstant(), sysLocalZone);

        return sysLocalZdtObject;
    }

    /** getWeek calculates and returns start and end of current week.
     * @param today  */
    public static LocalDateTime[] getWeek(LocalDateTime today){
        LocalDateTime[] startStop;
        startStop = new LocalDateTime[2];
        System.out.println(today.getDayOfWeek());
        int currYear = today.getYear();
        int currMonth = today.getMonthValue();
        DayOfWeek currDay = today.getDayOfWeek();

        HashMap<DayOfWeek, Integer> weekCalcMap = new HashMap<DayOfWeek, Integer>();
        weekCalcMap.put(DayOfWeek.MONDAY, 0);
        weekCalcMap.put(DayOfWeek.TUESDAY, 1);
        weekCalcMap.put(DayOfWeek.WEDNESDAY, 2);
        weekCalcMap.put(DayOfWeek.THURSDAY, 3);
        weekCalcMap.put(DayOfWeek.FRIDAY, 4);
        weekCalcMap.put(DayOfWeek.SATURDAY, 5);
        weekCalcMap.put(DayOfWeek.SUNDAY, 6);

        int adjustmentAmount = weekCalcMap.get(currDay);

        LocalDateTime startWindow = today.minus(adjustmentAmount, ChronoUnit.DAYS);
        LocalDateTime endWindow = startWindow.plus(6, ChronoUnit.DAYS);

        startStop[0] = startWindow;
        startStop[1] = endWindow;
        System.out.println("now: " + today);
        System.out.println("start: " + startWindow);
        System.out.println("end: " + endWindow);

        return startStop;
    }

}
