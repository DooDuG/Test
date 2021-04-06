package comma2.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comma2.Shopping;


@Controller
public class comma2Controller {
	
	@Autowired
	Shopping shopping;
	

	@RequestMapping(value="/keyword1")
	public String keyword1() {
		return "thymeleaf/html/keyword1" ;
	}
	
	@RequestMapping(value="/keyword2")
	public String keyword2(@RequestParam(value="keyInput")String keyInput, Model model) {
		List<Map<String, Object>> items = shopping.apiAction(keyInput);
		System.out.println("컨트롤러 시스아웃 = "+items.toString());
		model.addAttribute("keyInput",keyInput);
		model.addAttribute("items",items);
		
		return "thymeleaf/html/keyword1" ;
	}
	
	
}
