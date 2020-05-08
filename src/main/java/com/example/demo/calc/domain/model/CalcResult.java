package com.example.demo.calc.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CalcResult {

	private String formulaId;
	private String formulaName;
	private LocalDate resultDate;
	private String valueSet;
	private int designerDay;
}
