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
      .withPassportNumber("CA-SARAH-1")
      .withName("Sarah Murphy")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber("CA-SIMON-2")
      .withName("Simon Murphy")
      .withUnusedVisaPages(0)
      .withExpiresOn(LocalDate.of(2045, Month.MAY, 11))
      .withAge(12)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber("AU-EMILY-3")
      .withName("Emily Brown")
      .withUnusedVisaPages(20)
      .withExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25))
      .withAge(16)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber("AU-JAMES-4")
      .withName("James Brown")
      .withUnusedVisaPages(10)
      .withExpiresOn(LocalDate.of(2045, Month.APRIL, 10))
      .withAge(17)
      .build());

    return passports;
  }

}
