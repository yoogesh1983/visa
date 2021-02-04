package com.yoogesh.visa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import com.yoogesh.visa.event.AgendaGroupEventListener;
import com.yoogesh.visa.model.Passport;
import com.yoogesh.visa.model.Visa;
import com.yoogesh.visa.model.VisaApplication;
import com.yoogesh.visa.repository.ApplicationRepository;

public class Run {

	public static void main(String[] args) {

		boolean setAgendaHalfAndHalf = false;

		List<Passport> passports = ApplicationRepository.getPassports();
		List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();

		if (promptForYesNoQuestion("Do you want to set AgendaGroup half via Java and half via drools file?")) {
			setAgendaHalfAndHalf = true;
		}

		if (promptForYesNoQuestion("Do you want to set all passports as expired?")) {
			passports.forEach(passport -> passport.setExpiresOn(LocalDate.MIN));
		}

		KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		KieSession ksession = kieContainer.newKieSession("StatefulPassportValidation");

		if (promptForYesNoQuestion("Do you want to enable the event listener?")) {
			ksession.addEventListener(new AgendaGroupEventListener(System.out));
		}

		passports.forEach(ksession::insert);
		visaApplications.forEach(ksession::insert);

		Agenda agenda = ksession.getAgenda();

		if (setAgendaHalfAndHalf) {
			agenda.getAgendaGroup("validate-passport").setFocus();
		} else {
			agenda.getAgendaGroup("issue-visa").setFocus();
			agenda.getAgendaGroup("validate-application").setFocus();
			agenda.getAgendaGroup("validate-passport").setFocus();
		}

		System.out.println("==== Drools session Start ==== \n");
		ksession.fireAllRules();
		ksession.dispose();
		System.out.println("\n==== Drools session End ==== \n");

		Collection<?> visaObjects = ksession.getObjects(o -> o.getClass() == Visa.class);
		System.out.println("== Visas from session == ");
		visaObjects.forEach(System.out::println);
	}

	public static boolean promptForYesNoQuestion(String question) {
		String enteredStr = "";

		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.print(String.format("%s Enter 'yes' or 'no': ", question));
				enteredStr = br.readLine().trim();

				if ("yes".equalsIgnoreCase(enteredStr) || "y".equalsIgnoreCase(enteredStr)) {
					return true;
				}

				if ("no".equalsIgnoreCase(enteredStr) || "n".equalsIgnoreCase(enteredStr)) {
					return false;
				}

				System.out.println("Enter either 'yes' or 'no'");
			} catch (IOException e) {
				System.out.println("Invalid input: " + enteredStr);
			}
		}

	}

}