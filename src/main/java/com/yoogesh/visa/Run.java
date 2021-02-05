package com.yoogesh.visa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.yoogesh.visa.model.Passport;
import com.yoogesh.visa.model.Visa;
import com.yoogesh.visa.model.VisaApplication;
import com.yoogesh.visa.repository.ApplicationRepository;

public class Run {

	public static void main(String[] args) {

		List<Passport> passports = ApplicationRepository.getPassports();
		List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();

		KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		KieSession ksession = kieContainer.newKieSession("StatefulPassportValidation");

		if ((promptForYesNoQuestion("Do you want to enable the INSERT, UPDATE and DELETE event?"))) {
			ksession.addEventListener(new RuleRuntimeEventListener() {
				@Override
				public void objectInserted(ObjectInsertedEvent event) {
					System.out.println("==> " + event.getObject() + " INSERTED");
				}

				@Override
				public void objectUpdated(ObjectUpdatedEvent event) {
					System.out.println("==> " + event.getObject() + " UPDATED");

				}

				@Override
				public void objectDeleted(ObjectDeletedEvent event) {
					System.out.println("==> " + event.getOldObject() + " DELETED");
				}
			});
		}

		passports.forEach(ksession::insert);
		visaApplications.forEach(ksession::insert);

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