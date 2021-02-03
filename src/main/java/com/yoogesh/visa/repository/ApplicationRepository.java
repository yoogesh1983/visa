package com.yoogesh.visa.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.yoogesh.visa.model.Passport;
import com.yoogesh.visa.model.VisaApplication;

public class ApplicationRepository {

	private static final String YOOGESH_PASSPORT_NUMBER = "CA-YOOGESH-1";
	private static final String SUSHILA_PASSPORT_NUMBER = "CA-SUSHILA-2";
	private static final String KRISTY_PASSPORT_NUMBER = "AU-KRISTY-3";
	private static final String KRISHA_PASSPORT_NUMBER = "AU-KRISHA-4";

	public static List<Passport> getPassports() {
		List<Passport> passports = new ArrayList<>();

		passports.add(Passport.newBuilder().withPassportNumber(YOOGESH_PASSPORT_NUMBER).withName("Yoogesh Sharma")
				.withUnusedVisaPages(1).withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17)).withAge(50).build());

		passports.add(Passport.newBuilder().withPassportNumber(SUSHILA_PASSPORT_NUMBER).withName("Sushila Sapkota")
				.withUnusedVisaPages(0).withExpiresOn(LocalDate.of(2045, Month.MAY, 11)).withAge(12).build());

		passports.add(Passport.newBuilder().withPassportNumber(KRISTY_PASSPORT_NUMBER).withName("Kristy Sharma")
				.withUnusedVisaPages(20).withExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25)).withAge(16).build());

		passports.add(Passport.newBuilder().withPassportNumber(KRISHA_PASSPORT_NUMBER).withName("Krisha Sharma")
				.withUnusedVisaPages(10).withExpiresOn(LocalDate.of(2045, Month.APRIL, 10)).withAge(17).build());

		return passports;
	}

	public static List<VisaApplication> getVisaApplications() {
		List<VisaApplication> visaApplications = new ArrayList<>();

		visaApplications.add(VisaApplication.newBuilder().withApplicationId(1)
				.withPassportNumber(YOOGESH_PASSPORT_NUMBER).withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
				.withVisitEndDate(LocalDate.of(2040, Month.JANUARY, 4)).build());

		visaApplications.add(VisaApplication.newBuilder().withApplicationId(2)
				.withPassportNumber(SUSHILA_PASSPORT_NUMBER).withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
				.withVisitEndDate(LocalDate.of(2039, Month.JANUARY, 4)).build());

		visaApplications.add(VisaApplication.newBuilder().withApplicationId(3)
				.withPassportNumber(KRISTY_PASSPORT_NUMBER).withVisitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
				.withVisitEndDate(LocalDate.of(2044, Month.MARCH, 31)).build());

		visaApplications.add(VisaApplication.newBuilder().withApplicationId(4)
				.withPassportNumber(KRISHA_PASSPORT_NUMBER).withVisitStartDate(LocalDate.of(2045, Month.JANUARY, 1))
				.withVisitEndDate(LocalDate.of(2045, Month.MARCH, 10)).build());

		return visaApplications;
	}

}
