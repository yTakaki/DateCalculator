package com.example.demo.calc.domain.service;

import java.util.List;

import com.example.demo.calc.domain.model.Formula;

public interface FormulaService {

	public boolean insert(Formula formula);

	public Formula selectOne(String formulaId);

	public List<Formula> selectAll();

	public boolean update(Formula formula);

	public boolean delete(String formulaId);

}
