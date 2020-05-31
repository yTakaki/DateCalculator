package com.example.demo.calc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.calc.domain.model.CalcDateForm;
import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.service.CalcService;

@Controller
public class HomeCalcController {

	@Autowired
	private CalcService calcService;

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

}
