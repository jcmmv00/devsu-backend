package com.devsu.devsu.core.exceptions;

public class DailyLimitExceeded extends Exception {

    public DailyLimitExceeded() {
        super("The maximum withdrawal value in 1 day has been exceeded");
    }

}
