package org.sid.service;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;


public interface IBamqueService {
	
	public Compte consulterCompte(String codecpte);
	public void verser(String codecpte, double montant);
	public void retirer(String codecpte, double montant);
	public void virement(String codecpte1, String codecpte2, double montant);
	public Page<Operation> listOperation(String codecpte, int page, int size);
	
}
