package utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Converts {

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
