package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.service.IBamqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Controller
public class BanqueControlleur {
	@Autowired
	private IBamqueService banqueService;
	
	@RequestMapping("/operations")
	public String index(){
		
		return "comptes";
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCpmpte){
		model.addAttribute("codeCompte", codeCpmpte);
			try{
				Compte compte = banqueService.consulterCompte(codeCpmpte);
				Page<Operation> pageOperation = banqueService.listOperation(
						codeCpmpte, 0, 4);
				model.addAttribute("listeOperation", pageOperation.getContent());
				
				model.addAttribute("compte", compte);
			} catch (Exception e) {
				model.addAttribute("exception", e);
			}
		
		return "comptes";
	}
	
	@RequestMapping(value="/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, 
			String codeCompte, double montant, String codeCompte2){
		try{
			if(typeOperation.equals("VERS")){
				banqueService.verser(codeCompte, montant);
			} else if(typeOperation.equals("RET")){
				banqueService.retirer(codeCompte, montant);
			} else if(typeOperation.equals("VIR")){
				banqueService.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e){
			model.addAttribute("exception2", e);
		}
		
		
		return "redirect:/consulterCompte?codeCpmpte="+codeCompte;
	}
	
	
}
