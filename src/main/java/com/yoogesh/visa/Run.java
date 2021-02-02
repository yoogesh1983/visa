package com.yoogesh.visa;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.yoogesh.visa.model.Passport;
import com.yoogesh.visa.repository.ApplicationRepository;

public class Run {

	public static void main(String[] args) {
		
	    List<Passport> passports = ApplicationRepository.getPassports();

	    KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	    KieSession ksession = kieContainer.newKieSession("StatefulPassportValidation");
	    passports.forEach(ksession::insert);
	    
	    System.out.println("==== DROOLS SESSION END ==== ");
	    ksession.fireAllRules();
	    ksession.dispose();
	    System.out.println("==== DROOLS SESSION END ==== ");
	    
	    System.out.println("==== PASSPORTS AFTER DROOLS SESSION === ");
	    passports.forEach(passport -> {
	      System.out.println(passport + " verdict: " + passport.getValidation() + ("" != passport.getCause() ? ", cause: " + passport.getCause() : ""));
	    });
	}

}
