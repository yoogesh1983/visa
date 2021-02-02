package com.yoogesh.visa.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.yoogesh.visa.model.Passport;


public class ApplicationRepository {

  public static List<Passport> getPassports() {
    List<Passport> passports = new ArrayList<>();

    passports.add(Passport.newBuilder()
      .withPassportNumber("CA-YOOGESH-1")
      .withName("Yoogesh Sharma")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber("CA-SUSHILA-2")
      .withName("Sushila Sapkota")
      .withUnusedVisaPages(0)
      .withExpiresOn(LocalDate.of(2045, Month.MAY, 11))
      .withAge(12)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber("AU-KRISTY-3")
      .withName("Kristy Sharma")
      .withUnusedVisaPages(20)
      .withExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25))
      .withAge(16)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber("AU-KRISHA-4")
      .withName("Krisha Sharma")
      .withUnusedVisaPages(10)
      .withExpiresOn(LocalDate.of(2045, Month.APRIL, 10))
      .withAge(17)
      .build());

    return passports;
  }

}
