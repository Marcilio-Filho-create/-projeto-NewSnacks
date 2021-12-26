package ifrn.pi.snacks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.pi.snacks.models.Item;
import ifrn.pi.snacks.repositories.ItemRepository;

@Controller
@RequestMapping("/snacks")
public class SnacksController {
	
	@Autowired
	private ItemRepository ir;
	
	
	@GetMapping("/addItem")
	public ModelAndView form(Item item) {
		ModelAndView md = new ModelAndView();
		
		md.setViewName("itensAdd");
		List<Item> itens = ir.findAll();
		md.addObject("itens", itens);
		
		return md;
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(){
		return "cadastro";
	}
	
	@GetMapping("/logar")
	public String logar(){
		return "login";
	}
	
	@PostMapping("/salvarItem")
	public ModelAndView salvarItem(Item item, RedirectAttributes attributes) {
		ModelAndView md = new ModelAndView();
		
		ir.save(item);
		md.setViewName("redirect:/snacks/addItem");
		attributes.addFlashAttribute("mensagem", "Item adicionado a lista com sucesso!");
		return md;
	}
	
	@GetMapping("/addItem/{id}/deletar")
	public String apagarItem(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Item> opt = ir.findById(id);
		
		if(!opt.isEmpty()) {
			ir.delete(opt.get());
			attributes.addFlashAttribute("mensagem", "Item removido da lista com sucesso!");
		}
		
		return "redirect:/snacks/addItem";
	}
	
	/*@GetMapping("/addItem/{idI}/editar")
	public ModelAndView selecionarItem(@PathVariable Long idI) {
		ModelAndView md = new ModelAndView();
		Optional<Item> opt = ir.findById(idI);
		
		if(opt.isEmpty()) {
			md.setViewName("/snacks/addItem");
			return md;
		}
		else {
			md.setViewName("itensAdd");
			md.addObject("item", opt.get());
			List<Item> itens = ir.findAll();
			md.addObject("itens", itens);
			return md;
		}
	}*/
}
