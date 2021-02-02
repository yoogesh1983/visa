package com.yoogesh.visa;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.yoogesh.visa.model.Passport;
import com.yoogesh.visa.repository.ApplicationRepository;

public class Run {

	public static void main(String[] args) {
		
	    List<Passport> passports = ApplicationRepository.getPassports();

	    KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	    StatelessKieSession kieSession = kieContainer.newStatelessKieSession("StatelessPassportValidation");
	    System.out.println("==== DROOLS SESSION START ==== ");
	    kieSession.execute(passports);
	    
	    //passports.forEach(password -> System.out.println(String.format("password %s Validation Result: %s", password, password.getValidation())));
	    System.out.println("==== DROOLS SESSION END ==== ");
		
	}

}
