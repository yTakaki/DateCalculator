package com.example.demo.calc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calc.domain.model.RegistFormulaForm;

@Controller
public class UpdateFormulaController {

	@GetMapping("/update")
	public String getUpdateFormula(@ModelAttribute RegistFormulaForm form,Model model) {

		return "calc/updateFormula";
	}

	@PostMapping("/update")
	public String postUpdateFormula(@ModelAttribute RegistFormulaForm form,BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return getUpdateFormula(form,model);
		}
		System.out.println(form);
		return "redirect:/home";
	}
}