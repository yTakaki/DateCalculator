package com.example.demo.calc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calc.domain.model.CalcDateForm;
import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.service.CalcService;
import com.example.demo.calc.domain.service.FormulaService;

@Controller
public class HomeCalcController {

	@Autowired
	private CalcService calcService;

	@Autowired
	private FormulaService formulaService;

	@GetMapping("/home")
	public String getHomeCalculator(@ModelAttribute CalcDateForm form,Model model) {
		return "calc/homeCalculator";
	}

	@PostMapping("/home")
	public String postHomeCalculator(@ModelAttribute @Validated CalcDateForm form,BindingResult bind,Model model) {
		if (bind.hasErrors()) {
			return "calc/homeCalculator";
		}
		System.out.println(form);
		List<CalcResult> resultList = calcService.calculation(form.getCalcDate());
		model.addAttribute("resultList",resultList);
		return "calc/homeCalculator";
	}

	@PostMapping("/delete/{id}")
	public String getDeleteFormula(Model model,@PathVariable("id")String formulaId) {
		System.out.println("formulaId="+formulaId);
		boolean result = formulaService.delete(formulaId);
		if (result) {
			System.out.println("delete success.");
			model.addAttribute("result","計算式("+formulaId+")を1件、削除しました。");
		} else {
			System.out.println("delete failure.");
			model.addAttribute("result","計算式("+formulaId+")の削除に失敗しました。");
		}
		model.addAttribute("calcDateForm",new CalcDateForm());
		return "calc/homeCalculator";
	}

}
