package com.example.demo.calc.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.calc.domain.model.CalcResult;
import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.mybatis.FormulaMapper;

@Service
public class CalcService {

	List<CalcResult> calcList = new ArrayList<CalcResult>();

	@Autowired
	private FormulaMapper mapper;

	public List<CalcResult> calculation(LocalDate calcDate) {

		List<CalcResult> resultList = new ArrayList<>();
		List<Formula> formulaList = mapper.selectAll();

		for (Formula formula:formulaList) {
			LocalDate resultDate;
			if (formula.getDesignerDay()==0) {
				resultDate = calcDate.plusYears(formula.getValueYear())
						.plusMonths(formula.getValueMonth()).plusDays(formula.getValueDay());
			} else if (formula.getDesignerDay()==29) {
				resultDate = calcDate.plusYears(formula.getValueYear())
						.plusMonths(formula.getValueMonth()+1).plusDays(1-calcDate.getDayOfMonth()).minusDays(1);
			} else {
				resultDate = calcDate.plusYears(formula.getValueYear()).plusMonths(formula.getValueMonth());
				if (resultDate.getDayOfMonth()>formula.getDesignerDay()) {
					resultDate = resultDate.minusDays(resultDate.getDayOfMonth()-formula.getDesignerDay());
				} else {
					resultDate = resultDate.minusDays(formula.getDesignerDay()-resultDate.getDayOfMonth());
				}
			}
			CalcResult calcResult = new CalcResult(formula.getFormulaId(),formula.getFormulaName(),resultDate,
					formula.getValueYear()+"/"+formula.getValueMonth()+"/"+formula.getValueDay(),
					formula.getDesignerDay());
			resultList.add(calcResult);
		}
		System.out.println("calculation success");
		return resultList;
	}

}
