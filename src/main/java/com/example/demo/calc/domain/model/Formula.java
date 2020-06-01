package com.example.demo.calc.domain.model;

import lombok.Data;

@Data
public class Formula {

	private String formulaId;

	private String formulaName;

	private int valueYear;

	private int valueMonth;

	private int valueDay;

	private int designerDay;

	public Formula() {
	}

	public Formula(String id,String name,int y,int m,int d,int designerDay) {
		this.formulaId = id;
		this.formulaName = name;
		this.valueYear = y;
		this.valueMonth = m;
		this.valueDay = d;
		this.designerDay = designerDay;
	}
}
