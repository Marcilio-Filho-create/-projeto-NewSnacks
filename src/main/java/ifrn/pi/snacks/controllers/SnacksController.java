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
	
	@GetMapping("/cardapio")
	public ModelAndView cardapio(){
		ModelAndView md = new ModelAndView();
		
		md.setViewName("cardapio");
		List<Item> itens = ir.findAll();
		md.addObject("itens", itens);
		return md;
	}
	
	@PostMapping("/salvarItem")
	public String salvarItem(Item item, RedirectAttributes attributes) {
		
		if(item.getTipo() == "Bebida") {
			item.setLink("");
		}else if(item.getTipo() == "Sanduiche") {
			item.setLink("https://media.istockphoto.com/vectors/burger-icon-vector-isolated-on-white-background-burger-sign-vector-id1029096298?k=20&m=1029096298&s=170667a&w=0&h=Cox0Ks6_3t5Jdju5r8lh6doGX9KegutP3tN-hGrEYW4=");
		}else if(item.getTipo() == "Pizza") {
			item.setLink("");
		}else if(item.getTipo() == "Prato") {
			item.setLink("");
		}
		
		ir.save(item);
		attributes.addFlashAttribute("mensagem", "Item adicionado a lista com sucesso!");
		System.out.println(item);
		return "redirect:/snacks/addItem";
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
	
	@GetMapping("/addItem/{id}/editarCardapio")
	public String editarCardapio(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Item> opt = ir.findById(id);
		
		if(!opt.isEmpty()) {
			Item item = opt.get();
			if(opt.get().getStatus() == true) {
				item.setStatus(false);
				ir.save(item);
				attributes.addFlashAttribute("mensagem", "Item removido do cardápio com sucesso!");
			}else if(opt.get().getStatus() == false) {
				item.setStatus(true);
				ir.save(item);
				attributes.addFlashAttribute("mensagem", "Item adicionado ao cardápio com sucesso!");
			}
		}
		
		return "redirect:/snacks/addItem";
	}
	
	@GetMapping("/addItem/{idI}/editar")
	public ModelAndView selecionarItem(@PathVariable Long idI) {
		ModelAndView md = new ModelAndView();
		Optional<Item> opt = ir.findById(idI);

		System.out.println(opt.get());
		
		if(opt.isEmpty()) {
			md.setViewName("/snacks/addItem");
		}
		else {
			md.setViewName("itensAdd");
			md.addObject("item", opt.get());
			List<Item> itens = ir.findAll();
			md.addObject("itens", itens);
		}
		System.out.println(opt.get());
		return md;
	}
}
