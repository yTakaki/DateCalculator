package com.example.demo.calc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.model.RegistFormulaForm;
import com.example.demo.calc.domain.service.FormulaService;

@Controller
public class UpdateFormulaController {

	@Autowired
	private FormulaService formulaService;

	@GetMapping("/update/{id}")
	public String getUpdateFormula(@ModelAttribute RegistFormulaForm form,Model model,
			@PathVariable("id")String formulaId) {
		System.out.println("formulaId="+formulaId);

		if (formulaId!=null && formulaId.length()>0) {
			Formula formula = formulaService.selectOne(formulaId);
			System.out.println(formula);
			form.setFormulaId(formula.getFormulaId());
			form.setFormulaName(formula.getFormulaName());
			form.setValueYear(formula.getValueYear());
			form.setValueMonth(formula.getValueMonth());
			form.setValueDay(formula.getValueDay());
			form.setDesignerDay(formula.getDesignerDay());
			model.addAttribute("registFormulaForm",form);
		}

		return "calc/updateFormula";
	}

	@PostMapping("/update")
	public String postUpdateFormula(@ModelAttribute @Validated RegistFormulaForm form,
			BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return getUpdateFormula(form,model,form.getFormulaId());
		}
		System.out.println(form);

		Formula formula = new Formula();

		formula.setFormulaId(form.getFormulaId());
		formula.setFormulaName(form.getFormulaName());
		formula.setValueYear(form.getValueYear());
		formula.setValueMonth(form.getValueMonth());
		formula.setValueDay(form.getValueDay());
		formula.setDesignerDay(form.getDesignerDay());

		boolean result = formulaService.update(formula);
		if (result==true) {
			System.out.println("update success.");
			model.addAttribute("result","計算式("+form.getFormulaId()+")を1件、更新しました。");
		} else {
			System.out.println("update failure.");
			model.addAttribute("result","計算式("+form.getFormulaId()+")の更新に失敗しました。");
		}

		return "calc/homeCalculator";
	}


}
