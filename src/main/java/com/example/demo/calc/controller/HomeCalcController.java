package com.example.demo.calc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.service.CalcService;

@Controller
public class HomeCalcController {

	@Autowired
	private CalcService calcService;

	@GetMapping("/home")
	public String getHomeCalculator(Model model) {
		return "calc/homeCalculator";
	}

	@PostMapping("/home")
	public String postHomeCalculator(@RequestParam("calcDate") String calcDate,Model model) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate ldate = LocalDate.parse(calcDate,fmt);
		List<CalcResult> resultList = calcService.calculation(ldate);
		model.addAttribute("resultList",resultList);
		return "calc/homeCalculator";
	}
}
