package org.sid.service;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// Transactional means that all the instruction inside method must be done to achive the methode 
@Service
@Transactional
public class IBamqueServiceImplementation implements IBamqueService{
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Compte consulterCompte(String codecpte) {
		Compte cpt = compteRepository.findOne(codecpte);
		if (cpt == null) throw new RuntimeException("Compte Introuvable");
		return cpt;
	}

	@Override
	public void verser(String codecpte, double montant) {
		
		Compte cpt = consulterCompte(codecpte);
		Versement vsm = new Versement(new Date(), montant, cpt);
		operationRepository.save(vsm);
		cpt.setSolde(cpt.getSolde() + montant);
		compteRepository.save(cpt);
	}

	@Override
	public void retirer(String codecpte, double montant) {
		Compte cpt = consulterCompte(codecpte);
		
		double faciliteCaisse = 0;
		
		if (cpt instanceof CompteCourant ) {
			faciliteCaisse = ((CompteCourant) cpt).getDecouvert() ;
		}
		
		if (cpt.getSolde() + faciliteCaisse < montant ) 
			throw new RuntimeException("Impossible Solde + decouver sont inferieur au montatnt");
		
		Retrait rt = new Retrait(new Date(), montant, cpt);
		operationRepository.save(rt);
		cpt.setSolde(cpt.getSolde() - montant);
		compteRepository.save(cpt);
		
	}

	@Override
	public void virement(String codecpte1, String codecpte2, double montant) {
		retirer(codecpte1, montant);
		verser(codecpte1, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codecpte, int page, int size) {
		return operationRepository.listeOperation
				(codecpte, new PageRequest(page, size));
	}

}
