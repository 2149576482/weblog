package com.smallfish.weblog.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public interface Constants {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd");

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

}
