package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Versement;
import org.sid.service.IBamqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HamouBankApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientrepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private IBamqueService ibamqueService;
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HamouBankApplication.class, args);

		
		//  Seconde way how to add Client to database  it means second to have a bean of ClientRepository
		//		ClientRepository clientrepository = ctx.getBean(ClientRepository.class);
		//		clientrepository.save(new Client("hassan", "hassan@gmail.com"));
	}

	@Override
	public void run(String... arg0) throws Exception {
//		Client c1 = clientrepository.save(new Client("Hassan","hasan@gmail.com"));
//		Client c2 = clientrepository.save(new Client("Rachide","Rachide@gmail.com"));
//		
//		Compte cpt1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, c1, 5000));
//		Compte cpt2 = compteRepository.save(new CompteEpargne("c2", new Date(), 90000, c2, 5000));
//		
//		operationRepository.save(new Versement( new Date(), 1000, cpt1));
//		operationRepository.save(new Versement( new Date(), 1000, cpt1));
//		operationRepository.save(new Versement( new Date(), 1000, cpt1));
//		operationRepository.save(new Versement( new Date(), 1000, cpt1));
//		
//		operationRepository.save(new Versement( new Date(), 1000, cpt2));
//		operationRepository.save(new Versement( new Date(), 1000, cpt2));
//		operationRepository.save(new Versement( new Date(), 1000, cpt2));
//		operationRepository.save(new Versement( new Date(), 1000, cpt2));
//		
//		ibamqueService.verser(cpt1.getCodeCompte(), 1111);
//		
//		ibamqueService.retirer(cpt1.getCodeCompte(), 1100);
		
	}
	
}
