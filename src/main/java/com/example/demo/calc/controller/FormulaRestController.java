package com.example.demo.calc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.service.RestService;

@RestController
public class FormulaRestController {

	@Autowired
	RestService restService;

	@GetMapping("/formulaList/{id}")
	public Formula getSelectOne(@PathVariable("id")String formulaId) {

		return restService.selectOne(formulaId);
	}

	@GetMapping("/formulaList")
	public List<Formula> getSelectAll() {
		return restService.selectAll();
	}
}
