package com.example.demo.calc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeCalcController {

	@GetMapping("/homeCalc")
	public String getHomeCalculator(Model model) {
		return "calc/homeCalculator";
	}

	@PostMapping("/homeCalc")
	public String postHomeCalculator(Model model) {
		return "calc/homeCalculator";
	}
}
