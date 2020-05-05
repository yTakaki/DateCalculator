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

}
