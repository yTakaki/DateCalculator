package com.example.demo.calc.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegistFormulaForm {

	@NotBlank
	@Length(min=1,max=5)
	private String formulaId;

	@NotBlank
	@Length(min=1,max=50)
	private String formulaName;

	@NotNull
	private int valueYear;

	@NotNull
	private int valueMonth;

	@NotNull
	private int valueDay;

	@NotNull
	@Range(min=0,max=29)
	private int designerDay;

}
