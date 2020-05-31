package com.example.demo.calc.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.model.Formula;

@Service
public class CalcService {

	List<CalcResult> calcList = new ArrayList<CalcResult>();

	@Autowired
	private FormulaService formulaService;

	public List<CalcResult> calculation(LocalDate calcDate) {

		List<CalcResult> resultList = new ArrayList<>();
		List<Formula> formulaList = formulaService.selectAll();

		for (Formula formula:formulaList) {
			CalcResult calcResult = new CalcResult();
			calcResult.setFormulaId(formula.getFormulaId());
			calcResult.setFormulaName(formula.getFormulaName());
			calcResult.setValueSet(formula.getValueYear()+"/"+formula.getValueMonth()+"/"+formula.getValueDay());
			calcResult.setDesignerDay(formula.getDesignerDay());
			LocalDate resultDate;
			if (formula.getDesignerDay()==0) {
				resultDate = calcDate.plusYears(formula.getValueYear());
				resultDate = resultDate.plusMonths(formula.getValueMonth());
				resultDate = resultDate.plusDays(formula.getValueDay());
			} else if (formula.getDesignerDay()==29) {
				resultDate = calcDate.plusYears(formula.getValueYear());
				resultDate = resultDate.plusMonths(formula.getValueMonth()+1);
				resultDate = resultDate.plusDays(1-resultDate.getDayOfMonth());
				resultDate = resultDate.minusDays(1);
			} else {
				resultDate = calcDate.plusYears(formula.getValueYear());
				resultDate = resultDate.plusMonths(formula.getValueMonth());
				if (resultDate.getDayOfMonth()>formula.getDesignerDay()) {
					resultDate = resultDate.minusDays(resultDate.getDayOfMonth()-formula.getDesignerDay());
				} else {
					resultDate = resultDate.minusDays(formula.getDesignerDay()-resultDate.getDayOfMonth());
				}
			}
			calcResult.setResultDate(resultDate);
			resultList.add(calcResult);
		}
		System.out.println("calculation success");
		return resultList;
	}

}
