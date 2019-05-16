package com.example.myapplication.data.location;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeOffest {
    private long gmtOffset;

    public long getGmtOffset() {
        return gmtOffset;
    }

    public long getCurrentOffsert(){
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset()/1000;
        return gmtOffset-mGMTOffset;
    }
}
