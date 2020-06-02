package com.example.demo.calc.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CalcDateForm {

	@NotNull
	@DateTimeFormat(pattern="uuuu-MM-dd")
	private LocalDate calcDate;

}
