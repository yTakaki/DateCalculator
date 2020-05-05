package com.example.demo.calc.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegistFormulaForm {

	@NotBlank(groups=ValidGroup1.class)
	@Length(min=1,max=5,groups=ValidGroup2.class)
	private String formulaId;

	@NotBlank(groups=ValidGroup1.class)
	@Length(min=1,max=50,groups=ValidGroup2.class)
	private String formulaName;

	@NotNull(groups=ValidGroup1.class)
	private int valueYear;

	@NotNull(groups=ValidGroup1.class)
	private int valueMonth;

	@NotNull(groups=ValidGroup1.class)
	private int valueDay;

	@NotNull(groups=ValidGroup1.class)
	@Range(min=0,max=29,groups=ValidGroup2.class)
	private int designerDay;

}
