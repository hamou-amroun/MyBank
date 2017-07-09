package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String consulter(Model model, String codeCompte,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size ){
		model.addAttribute("codeCompte", codeCompte);
			try{
				Compte compte = banqueService.consulterCompte(codeCompte);
				Page<Operation> pageOperation = 
						banqueService.listOperation(codeCompte, page, size);
				model.addAttribute("listeOperation", pageOperation.getContent());
				int[] pages = new int[pageOperation.getTotalPages()];
				model.addAttribute("pages", pages);
				model.addAttribute("compte", compte);
			} catch (Exception e) {
				model.addAttribute("exception", e);
			}
		
		return "comptes";
	}
	
	@RequestMapping(value="/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant, String codeCompte2){
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
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
	
	
}
