package com.example.demo.calc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calc.domain.model.CalcDateForm;
import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.model.RegistFormulaForm;
import com.example.demo.calc.domain.service.FormulaService;

@Controller
public class RegistFormulaController {

	@Autowired
	private FormulaService formulaService;

	@GetMapping("/regist")
	public String getRegistFormula(@ModelAttribute RegistFormulaForm form,Model model) {

		return "calc/registFormula";
	}

	@PostMapping("/regist")
	public String postRegistFormula(@ModelAttribute @Validated RegistFormulaForm form,
			BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return getRegistFormula(form,model);
		}
		System.out.println(form);

		Formula formula = new Formula(form.getFormulaId(),form.getFormulaName(),
				form.getValueYear(),form.getValueMonth(),form.getValueDay(),form.getDesignerDay());

		boolean result = formulaService.insert(formula);
		if (result==true) {
			System.out.println("insert success.");
			model.addAttribute("result","計算式を1件、追加しました。");
		} else {
			System.out.println("insert failure.");
			model.addAttribute("result","計算式の追加に失敗しました。");
		}

		model.addAttribute("calcDateForm",new CalcDateForm());
		return "calc/homeCalculator";
	}

}
