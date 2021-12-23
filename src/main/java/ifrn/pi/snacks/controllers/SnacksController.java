package ifrn.pi.snacks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.snacks.models.Item;
import ifrn.pi.snacks.repositories.ItemRepository;

@Controller
@RequestMapping("/snacks")
public class SnacksController {
	
	@Autowired
	private ItemRepository ir;
	
	
	@GetMapping("/addItem")
	public String form(Item item) {
		return "itensAdd";
	}
	
	@PostMapping("/salvarItem")
	public String salvarItem(Item item) {
		ir.save(item);
		return "redirect:/snacks/addItem";
	}
}
