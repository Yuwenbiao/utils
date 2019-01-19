package com.example.util.时间戳判断;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CheckTimeStamp {
    public static void main(String[] args) {
        checkTimeStamp1();
//        checkTimeStamp2();
    }

    private static void checkTimeStamp1() {
        String timestamp = "20190119154159";
        //5S时间戳判断
        if (LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).plusSeconds(5).isBefore(LocalDateTime.now())) {
            System.out.println("请求已失效");
        }
    }

    private static void checkTimeStamp2() {
        try {
            String timestamp = "20190119142311";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            Date requestDate = simpleDateFormat.parse(timestamp);

            //5S时间戳判断
            Date validDate = new Date(requestDate.getTime() + 5 * 1000);
            if (validDate.before(new Date())) {
                System.out.println("时间已失效");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
