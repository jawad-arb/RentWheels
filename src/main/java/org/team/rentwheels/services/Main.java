package org.team.rentwheels.services;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Date insuranceEndDate=Date.valueOf("2023-02-12");
        Date endDate=Date.valueOf("2023-02-14");
        System.out.println(endDate.after(insuranceEndDate));
    }
}
