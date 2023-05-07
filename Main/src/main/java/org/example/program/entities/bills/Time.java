package org.example.program.entities.bills;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Time implements Serializable, Comparable<Time> {
    private Integer date;
    private Integer month;
    private Integer year;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private String stringTime;

    public void updateCurrentTime(){
        Date dateUtil = new Date();
        date = dateUtil.getDate();
        month = dateUtil.getMonth();
        year = dateUtil.getYear() + 1900;
        hour = dateUtil.getHours();
        minute = dateUtil.getMinutes();
        second = dateUtil.getSeconds();
        makeStringFromTime();
    }

    public void parseTimeFromString(){
        String[] arrStr = stringTime.split("/");
        date = Integer.parseInt(arrStr[0]);
        month = Integer.parseInt(arrStr[1]);
        year = Integer.parseInt(arrStr[2]);

        String[] dts = arrStr[3].split(":");
        hour = Integer.parseInt(dts[0]);
        minute= Integer.parseInt(dts[1]);
        second = Integer.parseInt(dts[2]);
    }

    public void makeStringFromTime(){
        stringTime = date+"/"+month+"/"+year+"/"+hour+":"+month+":"+second;
    }
    @Override
    public int compareTo(Time other) {
        int yearComparison = this.year.compareTo(other.year);
        if (yearComparison != 0) {
            return yearComparison;
        }

        int monthComparison = this.month.compareTo(other.month);
        if (monthComparison != 0) {
            return monthComparison;
        }

        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) {
            return dateComparison;
        }

        int hourComparison = this.hour.compareTo(other.hour);
        if (hourComparison != 0) {
            return hourComparison;
        }

        int minuteComparison = this.minute.compareTo(other.minute);
        if (minuteComparison != 0) {
            return minuteComparison;
        }
        return this.second.compareTo(other.second);
    }
}
