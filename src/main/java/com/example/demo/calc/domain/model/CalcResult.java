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

	public CalcResult() {
	}

	public CalcResult(String id,String name,LocalDate date,String value,int day) {
		this.formulaId = id;
		this.formulaName = name;
		this.resultDate = date;
		this.valueSet = value;
		this.designerDay = day;
	}
}
