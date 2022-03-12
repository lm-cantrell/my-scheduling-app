package helper;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class Time {


//    public ZoneId getUserZoneId(){
//        return  ZoneId.systemDefault();
//    }


    public static ZonedDateTime utcToSysLocal (ZonedDateTime utcZdtObject){
        ZoneId sysLocalZone = ZoneId.systemDefault();
        ZonedDateTime sysLocalZdtObject = ZonedDateTime.ofInstant(utcZdtObject.toInstant(), sysLocalZone);

        return sysLocalZdtObject;
    }

    public static ZonedDateTime utcToEastern (ZonedDateTime utcZdtObject) {
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime easternZdtObject = ZonedDateTime.ofInstant(utcZdtObject.toInstant(), newYorkZone);

        return easternZdtObject;
    }

    public static ZonedDateTime easternToLocalSys (ZonedDateTime eastZdtObj) {
        ZoneId sysLocalZone = ZoneId.systemDefault();
        ZonedDateTime sysLocalZdtObject = ZonedDateTime.ofInstant(eastZdtObj.toInstant(), sysLocalZone);

        return sysLocalZdtObject;
    }
}
