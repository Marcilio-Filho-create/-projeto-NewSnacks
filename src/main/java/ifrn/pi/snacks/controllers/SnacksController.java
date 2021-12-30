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
import ifrn.pi.snacks.models.Pedido;
import ifrn.pi.snacks.repositories.ItemRepository;
import ifrn.pi.snacks.repositories.PedidoRepository;


@Controller
@RequestMapping("/snacks")
public class SnacksController {
	
	@Autowired
	private ItemRepository ir;
	private PedidoRepository pr;
	
	@GetMapping("/addItem")
	public ModelAndView form(Item item) {
		ModelAndView md = new ModelAndView();
		
		md.setViewName("itensAdd");
		List<Item> itens = ir.findAll();
		md.addObject("itens", itens);
		
		return md;
	}
	
	
	@GetMapping()
	public ModelAndView listadepedidos() {
		
		List<Pedido> lista = pr.findAll();
		ModelAndView mv = new ModelAndView("/lista");
		mv.addObject("pedidos", lista);
		return mv;
		
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
		List<Item> itens = ir.findByStatus(true);
		md.addObject("itens", itens);
		return md;
	}
	
	@PostMapping("/salvarItem")
	public String salvarItem(Item item, RedirectAttributes attributes) {
		
		if(item.getTipo().equals("Bebida")) {
			item.setLink("https://images.pexels.com/photos/3551717/pexels-photo-3551717.png?auto=compress&cs=tinysrgb&dpr=1&w=500");
		}else if(item.getTipo().equals("Sanduiche")) {
			item.setLink("https://images.pexels.com/photos/1639557/pexels-photo-1639557.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
		}else if(item.getTipo().equals("Pizza")) {
			item.setLink("https://images.pexels.com/photos/2741457/pexels-photo-2741457.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
		}else if(item.getTipo().equals("Prato")) {
			item.setLink("https://media.istockphoto.com/photos/japanese-cuisine-chicken-karaage-bento-picture-id177361574?b=1&k=20&m=177361574&s=170667a&w=0&h=G35T_yp3wbx7e_lkicXXsqN0xJUkhrj7XMR8GW00fIM=");
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
