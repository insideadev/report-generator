package com.dev.reportgenerator;

public class Test3 {

    public static void main(String[] args) {
        double input = 15.63585464564;

        System.out.println("salary : " + input);

        double salary = Math.round(input *100.0) /100.0;

        System.out.println("salary : " + salary);
    }
}
