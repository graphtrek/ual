package co.grtk.ual.util;


import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@UtilityClass
public class DateUtil {

    public LocalDateTime toLocalDateTime(Date date) {
        if (Objects.nonNull(date)) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }

    public Date toDate(LocalDateTime localDateTime) {
        if (Objects.nonNull(localDateTime)) {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

}

