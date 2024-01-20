package com.kmsolutions.travelinsurance.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, DateTime> {

    private static final ThreadLocal<DateFormat> dateFormat
            = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    @Override
    public DateTime unmarshal(String s) throws Exception {
        return DateTime.parse(s);
    }

    @Override
    public String marshal(DateTime v) throws Exception {
        return dateFormat.get().format( new Date(v.getMillis()));
    }
}
