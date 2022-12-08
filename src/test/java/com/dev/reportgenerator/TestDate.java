package com.dev.reportgenerator;

import java.util.Date;

public class TestDate {


    private Date date;
    private int check;

    public TestDate( int check,Date date) {
        this.date = date;
        this.check = check;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
