package com.dev.reportgenerator;

import java.time.LocalDate;
import java.util.Date;

public class TestDate {


    private LocalDate date;
    private int check;

    public TestDate( int check,LocalDate date) {
        this.date = date;
        this.check = check;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
