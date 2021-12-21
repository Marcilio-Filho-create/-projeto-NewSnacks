package ifrn.pi.snacks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "/LoginNewSnacks/Login";
	}
	
	@RequestMapping("/newsnacks/cardapio")
	public String cardapio() {
		return "/LoginNewSnacks/Cardapio";
	}
	
}