package com.example.demo.calc.domain.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.model.Formula;

@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CalcServiceTest {

	@Autowired
	private CalcService service;

	@MockBean
	private FormulaService formulaService;

	@Test
	void calculation日加算Test() throws Exception {
		Formula formula = new Formula();
		{
			formula.setFormulaId("99999");
			formula.setFormulaName("testdata");
			formula.setValueDay(1);
		}
		List<Formula> testFormula = new ArrayList<>();
		{
			testFormula.add(0,formula);
		}
		when(formulaService.selectAll()).thenReturn(testFormula);
		List<CalcResult> actual = service.calculation(LocalDate.of(2020, 5, 5));
		assertThat(actual.get(0).getFormulaId(),is("99999"));
		assertThat(actual.get(0).getResultDate(),is(LocalDate.of(2020, 5, 6)));
	}

	@Test
	void calculation月加算Test() throws Exception {
		Formula formula = new Formula();
		{
			formula.setFormulaId("99999");
			formula.setFormulaName("testdata");
			formula.setValueMonth(1);
		}
		List<Formula> testFormula = new ArrayList<>();
		{
			testFormula.add(0,formula);
		}
		when(formulaService.selectAll()).thenReturn(testFormula);
		List<CalcResult> actual = service.calculation(LocalDate.of(2020, 5, 5));
		assertThat(actual.get(0).getFormulaId(),is("99999"));
		assertThat(actual.get(0).getResultDate(),is(LocalDate.of(2020, 6, 5)));
	}

	@Test
	void calculation年加算Test() throws Exception {
		Formula formula = new Formula();
		{
			formula.setFormulaId("99999");
			formula.setFormulaName("testdata");
			formula.setValueYear(1);
		}
		List<Formula> testFormula = new ArrayList<>();
		{
			testFormula.add(0,formula);
		}
		when(formulaService.selectAll()).thenReturn(testFormula);
		List<CalcResult> actual = service.calculation(LocalDate.of(2020, 5, 5));
		assertThat(actual.get(0).getFormulaId(),is("99999"));
		assertThat(actual.get(0).getResultDate(),is(LocalDate.of(2021, 5, 5)));
	}

	@Test
	void calculation日付指定Test() throws Exception {
		Formula formula = new Formula();
		{
			formula.setFormulaId("99999");
			formula.setFormulaName("testdata");
			formula.setDesignerDay(1);
		}
		List<Formula> testFormula = new ArrayList<>();
		{
			testFormula.add(0,formula);
		}
		when(formulaService.selectAll()).thenReturn(testFormula);
		List<CalcResult> actual = service.calculation(LocalDate.of(2020, 5, 5));
		assertThat(actual.get(0).getFormulaId(),is("99999"));
		assertThat(actual.get(0).getResultDate(),is(LocalDate.of(2020, 5, 1)));
	}

	@Test
	void calculation末日指定Test() throws Exception {
		Formula formula = new Formula();
		{
			formula.setFormulaId("99999");
			formula.setFormulaName("testdata");
			formula.setDesignerDay(29);
		}
		List<Formula> testFormula = new ArrayList<>();
		{
			testFormula.add(0,formula);
		}
		when(formulaService.selectAll()).thenReturn(testFormula);
		List<CalcResult> actual = service.calculation(LocalDate.of(2020, 5, 5));
		assertThat(actual.get(0).getFormulaId(),is("99999"));
		assertThat(actual.get(0).getResultDate(),is(LocalDate.of(2020, 5, 31)));
	}
}
