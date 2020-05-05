package com.example.demo.calc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calc.domain.model.GroupOrder;
import com.example.demo.calc.domain.model.RegistFormulaForm;

@Controller
public class RegistFormulaController {

	@GetMapping("/regist")
	public String getRegistFormula(@ModelAttribute RegistFormulaForm form,Model model) {

		return "calc/registFormula";
	}

	@PostMapping("/regist")
	public String postRegistFormula(@ModelAttribute @Validated(GroupOrder.class) RegistFormulaForm form,BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return getRegistFormula(form,model);
		}
		System.out.println(form);
		return "redirect:/home";
	}

}
